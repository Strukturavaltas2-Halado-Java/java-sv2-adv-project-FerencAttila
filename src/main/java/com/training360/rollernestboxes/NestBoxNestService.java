package com.training360.rollernestboxes;

import com.training360.rollernestboxes.nest.dtos.NestDto;
import com.training360.rollernestboxes.nest.dtos.SurveyCommand;
import com.training360.rollernestboxes.nest.dtos.ZoologyDataDto;
import com.training360.rollernestboxes.nest.exceptions.SurveyIsNotUniqueException;
import com.training360.rollernestboxes.nest.mapper.NestMapper;
import com.training360.rollernestboxes.nest.model.Nest;
import com.training360.rollernestboxes.nest.repository.NestRepository;
import com.training360.rollernestboxes.nestboxes.dtos.NestBoxDto;
import com.training360.rollernestboxes.nestboxes.dtos.NestBoxPuttingCommand;
import com.training360.rollernestboxes.nestboxes.dtos.UpdateNestBoxCommand;
import com.training360.rollernestboxes.nestboxes.exceptions.CannotDeleteNestBoxException;
import com.training360.rollernestboxes.nestboxes.exceptions.NestBoxNotFoundException;
import com.training360.rollernestboxes.nestboxes.mapper.NestBoxMapper;
import com.training360.rollernestboxes.nestboxes.model.Coordinates;
import com.training360.rollernestboxes.nestboxes.model.NestBox;
import com.training360.rollernestboxes.nestboxes.repository.NestBoxRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Service
@Transactional
@AllArgsConstructor
public class NestBoxNestService {

    private NestBoxRepository nestBoxRepository;

    private NestRepository nestRepository;

    private NestMapper nestMapper;

    private NestBoxMapper nestBoxMapper;

    public List<NestBoxDto> findAllNestBoxes() {
        return nestBoxRepository.findAll().stream()
                .map(nestBox -> nestBoxMapper.toNestBoxDto(nestBox))
                .toList();
    }

    public List<NestDto> findNests(Optional<String> nestBoxNumber, Optional<String> species) {
        nestBoxNumber = stripQueryParameters(nestBoxNumber);
        species = stripQueryParameters(species);
        isNestBoxExistsWithNestBoxNumber(nestBoxNumber);
        return nestRepository.findAllNestsByNestBoxNumberAndSpecies(nestBoxNumber, species).stream()
                .map(nest -> nestMapper.toNestDto(nest))
                .toList();
    }

    public NestBoxDto findNestBoxByNestBoxNumber(String nestBoxNumber) {
        return nestBoxMapper.toNestBoxDto(nestBoxRepository.findNestBoxByNestBoxNumber(nestBoxNumber.strip())
                .orElseThrow(() -> new NestBoxNotFoundException(nestBoxNumber)));
    }
    public List<ZoologyDataDto> getAllNestsAsZoologyData() {
        return nestRepository.findAll().stream()
                .map(nest -> nestMapper.toZoologyDataDto(nest))
                .sorted(Comparator.comparing(ZoologyDataDto::getDateOfSurvey))
                .toList();
    }

    public NestBoxDto saveNestBox(NestBoxPuttingCommand command) {
        NestBox nestBox = new NestBox(command.getNestBoxNumber().strip(),
                new Coordinates(command.getCoordinates().getEovX(),
                        command.getCoordinates().getEovY()),
                command.getDirection(),
                command.getHeight());
        nestBoxRepository.save(nestBox);
        return nestBoxMapper.toNestBoxDto(nestBox);
    }

    public NestDto saveNest(SurveyCommand command) {
        checkIfNestIsUnique(command.getNestBoxNumber().strip(), command.getDateOfSurvey(), command.getObserver().strip());
        NestBox nestBox = getNestBoxByNestBoxNumber(command.getNestBoxNumber().strip());
        Nest nest = new Nest(nestBox,
                command.getDateOfSurvey(),
                command.getSpecies().strip(),
                command.getNumberOfNestlings(),
                command.getObserver().strip());
        nestBox.addNest(nest);
        return nestMapper.toNestDto(nest);
    }

    public NestBoxDto updateNestBoxParameters(UpdateNestBoxCommand command) {
        NestBox nestBox = getNestBoxByNestBoxNumber(command.getNestBoxNumber().strip());
        nestBox.setDirection(command.getDirection());
        nestBox.setHeight(command.getHeight());
        return nestBoxMapper.toNestBoxDto(nestBox);
    }

    public void deleteNestBox(String nestBoxNumber) {
        NestBox nestBox = getNestBoxByNestBoxNumber(nestBoxNumber.strip());
        if (isNestBoxHasNests(nestBox)) {
            throw new CannotDeleteNestBoxException(nestBoxNumber);
        }
        nestBoxRepository.delete(nestBox);
    }

    private Optional<String> stripQueryParameters(Optional<String> parameter) {
        if (parameter.isPresent()) {
            return Optional.of(parameter.get().strip());
        }
        return parameter;
    }

    private boolean isNestBoxHasNests(NestBox nestBox) {
        return !nestBox.getNests().isEmpty();
    }

    private void checkIfNestIsUnique(String nestBoxNumber, LocalDate dateOfSurvey, String observer) {
        if (nestRepository.findNestByNestBox_NestBoxNumber(nestBoxNumber).stream()
                .anyMatch(checkObservationUniqueness(dateOfSurvey, observer))) {
            throw new SurveyIsNotUniqueException(nestBoxNumber, dateOfSurvey, observer);
        }
    }

    private Predicate<Nest> checkObservationUniqueness(LocalDate dateOfSurvey, String observer) {
        return nest -> nest.getDateOfSurvey().equals(dateOfSurvey) && nest.getObserver().equals(observer);
    }

    private NestBox getNestBoxByNestBoxNumber(String nestBoxNumber) {
        return nestBoxRepository.findNestBoxByNestBoxNumber(nestBoxNumber)
                .orElseThrow(() -> new NestBoxNotFoundException(nestBoxNumber));
    }

    private void isNestBoxExistsWithNestBoxNumber(Optional<String> nestBoxNumber) {
        if (nestBoxNumber.isPresent() && !nestBoxRepository.existsNestBoxByNestBoxNumber(nestBoxNumber.get().strip())) {
            throw new NestBoxNotFoundException(nestBoxNumber.get());
        }
    }
}
