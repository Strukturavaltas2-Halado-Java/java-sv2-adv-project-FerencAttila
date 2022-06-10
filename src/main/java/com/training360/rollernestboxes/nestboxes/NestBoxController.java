package com.training360.rollernestboxes.nestboxes;

import com.training360.rollernestboxes.nestboxes.dtos.NestBoxDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping(name = "/api/nestboxes")
public class NestBoxController {

    private NestBoxService service;

    @GetMapping
    public List<NestBoxDto> findNestBoxes(@RequestParam Optional<Boolean> isLiving) {
        return service.findAllNestBoxes(isLiving);
    }

    @GetMapping(value = "/{nestBoxId}")
    public NestBoxDto findNestBoxById(@PathVariable(value = "nestBoxId") String nestBoxId) {
        return service.findByNestBoxId(nestBoxId);
    }

    @GetMapping(value = "/{condition}")
    public  List<NestBoxDto> findAllNestBoxesByCondition(@PathVariable(value = "condition") Condition condition) {
        return service.findAllNestBoxesByCondition(condition);
    }
}
