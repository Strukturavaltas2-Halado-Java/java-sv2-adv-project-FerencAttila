package com.training360.rollernestboxes.nestboxes;

import com.training360.rollernestboxes.nestboxes.dtos.NestBoxDto;
import com.training360.rollernestboxes.nestboxes.dtos.NestBoxPlacementCommand;
import com.training360.rollernestboxes.nestboxes.exceptions.NestBoxNotFoundException;
import com.training360.rollernestboxes.nestboxes.model.Coordinates;
import com.training360.rollernestboxes.nestboxes.model.NestBox;
import com.training360.rollernestboxes.nestboxes.model.NestBoxParameters;
import com.training360.rollernestboxes.nestboxes.model.NestBoxPlacement;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Transactional
public class NestBoxService {

    private NestBoxRepository repository;

    private NestBoxMapper mapper;

    public List<NestBoxDto> findAllNestBoxes(Optional<Condition> condition) {
        return condition.map(value -> repository.findAllByConditionIs(value).stream()
                .map(nestBox -> mapper.toNestBoxDto(nestBox))
                .toList())
                .orElseGet(() -> repository.findAll().stream()
                .map(nestBox -> mapper.toNestBoxDto(nestBox))
                .toList());
    }

    public NestBoxDto findByNestBoxId(String nestBoxId) {
        return mapper.toNestBoxDto(repository.findByNestBoxIdIgnoreCase(nestBoxId)
                .orElseThrow(() -> new NestBoxNotFoundException(nestBoxId)));
    }

    public List<NestBoxDto> findAllLivingNestBoxes() {
        return repository.findAllByConditionIsNot(Condition.EXPIRED).stream()
                .map(nestBox -> mapper.toNestBoxDto(nestBox))
                .toList();
    }

    public NestBoxDto saveNestBox(NestBoxPlacementCommand command) {
       NestBox nestBox = createNestBoxByNewPlacement(command);
       repository.save(nestBox);
       return mapper.toNestBoxDto(nestBox);
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
