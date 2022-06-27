package com.training360.rollernestboxes.nestboxes.controller;

import com.training360.rollernestboxes.NestBoxNestService;
import com.training360.rollernestboxes.nestboxes.dtos.NestBoxDto;
import com.training360.rollernestboxes.nestboxes.dtos.NestBoxPuttingCommand;
import com.training360.rollernestboxes.nestboxes.dtos.UpdateNestBoxCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/nest-boxes")
@AllArgsConstructor
@Tag(name = "Operations on nest boxes", description = "Save, modify, delete and query nest boxes")
public class NestBoxController {

    private NestBoxNestService service;

    @GetMapping(value = "/all")
    @Operation(description = "Get all nest boxes in a list.")
    public List<NestBoxDto> findAllNestBoxes() {
        return service.findAllNestBoxes();
    }

    @GetMapping
    @Operation(description = "Get a nest box with given nest box number.")
    public NestBoxDto findNestBoxByNestBoxNumber(@RequestParam(name = "nest-box-number") String nestBoxNumber) {
        return service.findNestBoxByNestBoxNumber(nestBoxNumber);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponse(responseCode = "201", description = "Nest box created successfully")
    @Operation(description = "Save a nest box to the database.")
    public NestBoxDto saveNestBox(@Valid @RequestBody NestBoxPuttingCommand command) {
        return service.saveNestBox(command);
    }

    @PutMapping
    @Operation(description = "Update a nest box by nest box number, painted on nest box, with given parameters.")
    public NestBoxDto updateNestBoxParameters(@Valid @RequestBody UpdateNestBoxCommand command) {
        return service.updateNestBoxParameters(command);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(description = "Delete a nest box by given nest box number, painted on nest box.")
    public void deleteNestBox(@RequestParam(name = "nest-box-number") String nestBoxNumber) {
        service.deleteNestBox(nestBoxNumber);
    }
}
