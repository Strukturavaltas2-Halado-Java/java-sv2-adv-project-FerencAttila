package com.training360.rollernestboxes;

import com.training360.rollernestboxes.nestboxes.NestBoxMapper;
import com.training360.rollernestboxes.nestboxes.NestBoxRepository;
import com.training360.rollernestboxes.nestboxes.dtos.ExpireNestBoxCommand;
import com.training360.rollernestboxes.nestboxes.dtos.NestBoxDto;
import com.training360.rollernestboxes.nestboxes.dtos.NestBoxPlacementCommand;
import com.training360.rollernestboxes.nestboxes.dtos.UpdateNestBoxConditionCommand;
import com.training360.rollernestboxes.nestboxes.exceptions.InvalidConditionException;
import com.training360.rollernestboxes.nestboxes.exceptions.NestBoxAlreadyExpiredException;
import com.training360.rollernestboxes.nestboxes.exceptions.NestBoxNotFoundException;
import com.training360.rollernestboxes.nestboxes.model.*;
import com.training360.rollernestboxes.nesting.NestingMapper;
import com.training360.rollernestboxes.nesting.NestingRepository;
import com.training360.rollernestboxes.nesting.dtos.NestingDto;
import com.training360.rollernestboxes.nesting.dtos.SurveyCommand;
import com.training360.rollernestboxes.nesting.dtos.SurveyDto;
import com.training360.rollernestboxes.nesting.dtos.UpdateNestingCommand;
import com.training360.rollernestboxes.nesting.exceptions.InvalidSurveyDateException;
import com.training360.rollernestboxes.nesting.exceptions.NestingNotFoundException;
import com.training360.rollernestboxes.nesting.model.Nesting;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Transactional
public class NestBoxNestingService {

    private NestBoxRepository nestBoxRepository;

    private NestingRepository nestingRepository;

    private NestBoxMapper nestBoxMapper;

    private NestingMapper nestingMapper;

    public List<NestBoxDto> findAllNestBoxes(Optional<Condition> condition) {
        return condition.map(value -> nestBoxRepository.findAllByConditionIs(value).stream()
                        .map(nestBox -> nestBoxMapper.toNestBoxDto(nestBox))
                        .toList())
                .orElseGet(() -> nestBoxRepository.findAll().stream()
                        .map(nestBox -> nestBoxMapper.toNestBoxDto(nestBox))
                        .toList());
    }

    public NestBoxDto findByNestBoxId(String nestBoxId) {
        return nestBoxMapper.toNestBoxDto(getNestBoxByNestBoxId(nestBoxId));
    }

    public List<NestBoxDto> findAllLivingNestBoxes() {
        return nestBoxRepository.findAllByConditionIsNot(Condition.EXPIRED).stream()
                .map(nestBox -> nestBoxMapper.toNestBoxDto(nestBox))
                .toList();
    }

    public NestBoxDto saveNestBox(NestBoxPlacementCommand command) {
        NestBox nestBox = createNestBoxByNewPlacement(command);
        nestBoxRepository.save(nestBox);
        return nestBoxMapper.toNestBoxDto(nestBox);
    }

    public NestBoxDto updateNestBoxConditionByNestBoxId(UpdateNestBoxConditionCommand command) {
        NestBox nestBox = getNestBoxByNestBoxId(command.getNestBoxId());
        validateAndSetNestBoxCondition(nestBox, command.getCondition());
        return nestBoxMapper.toNestBoxDto(nestBox);
    }

    public NestBoxDto expireNestBox(ExpireNestBoxCommand command) {
        NestBox nestBox = getNestBoxByNestBoxId(command.getNestBoxId());
        validateNestBoxExpirationIsNotExists(nestBox);
        nestBox.setNestBoxExpiration(new NestBoxExpiration(
                command.getDateOfExpiry(),
                command.getDescriptionOfExpiry(),
                command.getReporterOfExpiry()));
        nestBox.setCondition(Condition.EXPIRED);
        return nestBoxMapper.toNestBoxDto(nestBox);
    }

    public void deleteByNestBoxId(String nestBoxId) {
        NestBox nestBox = getNestBoxByNestBoxId(nestBoxId);
        nestBoxRepository.delete(nestBox);
    }

    public void deleteAllNestBoxes() {
        nestBoxRepository.deleteAll();
    }

    public List<NestingDto> findAllNesting(Optional<Integer> year, Optional<String> species) {
        return getNestingByFilterValues(year, species).stream()
                .map(n -> nestingMapper.toNestingDto(n))
                .toList();
    }

    public List<NestingDto> findAllNestingByNestBoxId(String nestBoxId) {
        return nestingRepository.findAllByNestBoxId(nestBoxId).stream()
                .map(nesting -> nestingMapper.toNestingDto(nesting))
                .toList();
    }

    public SurveyDto saveNestingBySurvey(SurveyCommand command) {
        NestBox nestBox = updateNestBoxBySurvey(command);
        Nesting nesting = createNestingBySurvey(command, nestBox);
        nestingRepository.save(nesting);
        nestBox.addNesting(nesting);
        return nestingMapper.toSurveyDto(nesting);
    }

    public NestingDto updateNestingById(long id, UpdateNestingCommand command) {
        Nesting nesting = getNestingById(id);
        nesting.setNotes(command.getNotes());
        return nestingMapper.toNestingDto(nesting);
    }

    public void deleteNestingById(long id) {
        Nesting nesting = getNestingById(id);
        nestingRepository.delete(nesting);
    }

    private void validateAndSetNestBoxCondition(NestBox nestBox, Condition condition) {
        validateNestBoxCondition(nestBox, condition);
        nestBox.setCondition(condition);
    }

    private NestBox updateNestBoxBySurvey(SurveyCommand command) {
        NestBox nestBox = getNestBoxByNestBoxId(command.getNestBoxId());
        validateSurveyDate(nestBox, command);
        if (command.getCondition() != null) {
            validateAndSetNestBoxCondition(nestBox, command.getCondition());
        }
        return nestBox;
    }

    private void validateSurveyDate(NestBox nestBox, SurveyCommand command) {
        if (command.getNestingParametersCommand().getDateOfSurvey().isBefore(nestBox.getNestBoxPlacement().getDateOfPlacement())) {
            throw new InvalidSurveyDateException(command.getNestingParametersCommand().getDateOfSurvey(), "before");
        }
        if (nestBox.getNestBoxExpiration() != null
                && command.getNestingParametersCommand().getDateOfSurvey().isAfter(nestBox.getNestBoxExpiration().getDateOfExpiry())) {
            throw new InvalidSurveyDateException(command.getNestingParametersCommand().getDateOfSurvey(), "after");
        }
    }

    private List<Nesting> getNestingByFilterValues(Optional<Integer> year, Optional<String> species) {
        List<Nesting> nesting;
        if (year.isPresent() && species.isPresent()) {
            nesting = nestingRepository.findAllByYearAndSpecies(year.get(), species.get());
        } else if (year.isPresent()) {
            nesting = nestingRepository.findAllByYear(year.get());
        } else if (species.isPresent()) {
            nesting = nestingRepository.findAllBySpecies(species.get());
        } else {
            nesting = nestingRepository.findAll();
        }
        return nesting;
    }

    private void validateNestBoxExpirationIsNotExists(NestBox nestBox) {
        if (nestBox.getNestBoxExpiration() != null &&
                nestBox.getNestBoxExpiration().getDateOfExpiry() != null) {
            throw new NestBoxAlreadyExpiredException(nestBox.getNestBoxId());
        }
    }

    private void validateNestBoxCondition(NestBox nestBox, Condition condition) {
        if (condition == Condition.EXPIRED && nestBox.getNestBoxExpiration() == null ||
                condition != Condition.EXPIRED && nestBox.getNestBoxExpiration() != null) {
            throw new InvalidConditionException();
        }
    }

    private NestBox createNestBoxByNewPlacement(NestBoxPlacementCommand command) {
        return new NestBox(command.getNestBoxId(),
                new NestBoxPlacement(command.getDateOfPlacement(),
                        new NestBoxParameters(
                                new Coordinates(
                                        (int) Math.round(command.getNestBoxParametersCommand().getCoordinatesCommand().getEovX()),
                                        (int) Math.round(command.getNestBoxParametersCommand().getCoordinatesCommand().getEovY())),
                                command.getNestBoxParametersCommand().getHolder(),
                                command.getNestBoxParametersCommand().getHeight(),
                                command.getNestBoxParametersCommand().getOrientation(),
                                command.getNestBoxParametersCommand().getNestBoxType()),
                        command.getReporterOfPlacement()),
                Condition.GOOD,
                command.getNestBoxParametersCommand().getNotes());
    }

    private NestBox getNestBoxByNestBoxId(String nestBoxId) {
        return nestBoxRepository.findByNestBoxIdIgnoreCase(nestBoxId)
                .orElseThrow(() -> new NestBoxNotFoundException(nestBoxId));
    }

    private Nesting createNestingBySurvey(SurveyCommand command, NestBox nestBox) {
        return new Nesting(nestBox,
                nestingMapper.toNestingParameters(command.getNestingParametersCommand()),
                command.getNotesOnNestBox(),
                command.getObserver());
    }

    private Nesting getNestingById(long id) {
        return nestingRepository.findById(id)
                .orElseThrow(() -> new NestingNotFoundException(id));
    }
}
