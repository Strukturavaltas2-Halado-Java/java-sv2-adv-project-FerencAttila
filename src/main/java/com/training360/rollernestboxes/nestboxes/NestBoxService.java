package com.training360.rollernestboxes.nestboxes;

import com.training360.rollernestboxes.nestboxes.dtos.NestBoxDto;
import com.training360.rollernestboxes.nestboxes.exceptions.NestBoxNotFoundException;
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

    public List<NestBoxDto> findAllNestBoxes(Optional<Boolean> isLiving) {
        if (isLiving.isPresent() && Boolean.TRUE.equals(isLiving.get())) {
            return repository.findAllByConditionIsNot(Condition.EXPIRED).stream()
                    .map(nestBox -> mapper.toNestBoxDto(nestBox))
                    .toList();
        }
        return repository.findAll().stream()
                .map(nestBox -> mapper.toNestBoxDto(nestBox))
                .toList();
    }

    public NestBoxDto findByNestBoxId(String nestBoxId) {
        return mapper.toNestBoxDto(repository.findByNestBoxIdIgnoreCase(nestBoxId)
                .orElseThrow(() -> new NestBoxNotFoundException(nestBoxId)));
    }

    public List<NestBoxDto> findAllNestBoxesByCondition(Condition condition) {
        return repository.findAllByConditionIs(condition).stream()
                .map(nestBox -> mapper.toNestBoxDto(nestBox))
                .toList();
    }
}
