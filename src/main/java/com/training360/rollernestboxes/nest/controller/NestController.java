package com.training360.rollernestboxes.nest.controller;

import com.training360.rollernestboxes.NestBoxNestService;
import com.training360.rollernestboxes.nest.dtos.NestDto;
import com.training360.rollernestboxes.nest.dtos.SurveyCommand;
import com.training360.rollernestboxes.nest.dtos.ZoologyDataDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/nests")
@AllArgsConstructor
public class NestController {

    private NestBoxNestService service;

    @GetMapping
    @Operation(description = "Lists all nests, or list nests filtered by nest-box-number and/or nesting species.")
    public List<NestDto> findNests(@RequestParam(name = "nest-box-number") Optional<String> nestBoxNumber,
                                   @RequestParam Optional<String> species) {
        return service.findNests(nestBoxNumber, species);
    }

    @GetMapping(value = "/zoology-data")
    @Operation(description = "Lists all nests in format accepted by Zoology Database.")
    public List<ZoologyDataDto> getAllNestsAsZoologyData() {
        return service.getAllNestsAsZoologyData();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponse(responseCode = "201", description = "Nest saved successfully")
    @Operation(description = "Save nest by field observation data.")
    public NestDto saveNest(@Valid @RequestBody SurveyCommand command) {
        return service.saveNest(command);
    }
}
