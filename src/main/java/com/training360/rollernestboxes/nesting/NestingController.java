package com.training360.rollernestboxes.nesting;

import com.training360.rollernestboxes.NestBoxNestingService;
import com.training360.rollernestboxes.nesting.dtos.NestingDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/nesting")
@Tag(name = "Operations on nesting")
public class NestingController {

    private NestBoxNestingService service;

    @GetMapping
    @Operation(description = "Find all nesting, or nesting in given year or nestings by given species")
    public List<NestingDto> findAllNesting(@RequestParam Optional<Integer> year) {
        return service.findAllNesting(year);
    }
}
