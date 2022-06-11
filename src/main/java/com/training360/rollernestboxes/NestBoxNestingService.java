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

    public NestBoxDto updateNestBoxConditionByNestBoxId(String nestBoxId, UpdateNestBoxConditionCommand command) {
        NestBox nestBox = getNestBoxByNestBoxId(nestBoxId);
        validateNestBoxCondition(nestBox, command.getCondition());
        nestBox.setCondition(command.getCondition());
        return nestBoxMapper.toNestBoxDto(nestBox);
    }

    public NestBoxDto expireNestBox(ExpireNestBoxCommand command) {
        NestBox nestBox = getNestBoxByNestBoxId(command.getNestBoxId());
        validateNestBoxExpiration(nestBox);
        nestBox.setNestBoxExpiration(new NestBoxExpiration(command.getNestBoxId(),
                command.getDescription(), command.getReporter()));
        nestBox.setCondition(Condition.EXPIRED);
        return nestBoxMapper.toNestBoxDto(nestBox);
    }

    public void deleteByNestBoxId(String nestBoxId) {
        NestBox nestBox = getNestBoxByNestBoxId(nestBoxId);
        nestBoxRepository.delete(nestBox);
    }

    public void deleteAll() {
        nestBoxRepository.deleteAll();
    }

    public List<NestingDto> findAllNesting(Optional<Integer> year) {
        return year.map(value -> nestingRepository.findAllByYear(value).stream()
                        .map(nesting -> nestingMapper.toNestingDto(nesting))
                        .toList())
                .orElseGet(() -> nestingRepository.findAll().stream()
                        .map(nesting -> nestingMapper.toNestingDto(nesting))
                        .toList());
    }

    private void validateNestBoxExpiration(NestBox nestBox) {
        if (nestBox.getNestBoxExpiration() != null &&
                nestBox.getNestBoxExpiration().getDateOfExpiry() != null) {
            throw new NestBoxAlreadyExpiredException(nestBox.getNestBoxId());
        }
    }

    private void validateNestBoxCondition(NestBox nestBox, Condition condition) {
        if (nestBox.getNestBoxExpiration().getDateOfExpiry() == null && condition == Condition.EXPIRED) {
            throw new InvalidConditionException();
        }
        if (nestBox.getNestBoxExpiration().getDateOfExpiry() != null && condition != Condition.EXPIRED) {
            throw new InvalidConditionException();
        }
    }

    private NestBox getNestBoxByNestBoxId(String nestBoxId) {
        return nestBoxRepository.findByNestBoxIdIgnoreCase(nestBoxId)
                .orElseThrow(() -> new NestBoxNotFoundException(nestBoxId));
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
}
