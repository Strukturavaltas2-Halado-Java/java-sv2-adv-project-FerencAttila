package com.training360.rollernestboxes.nesting;

import com.training360.rollernestboxes.NestBoxNestingService;
import com.training360.rollernestboxes.nesting.dtos.NestingDto;
import com.training360.rollernestboxes.nesting.dtos.SurveyCommand;
import com.training360.rollernestboxes.nesting.dtos.SurveyDto;
import com.training360.rollernestboxes.nesting.dtos.UpdateNestingCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/nesting")
@Tag(name = "Operations on nesting")
public class NestingController {

    private NestBoxNestingService service;

    @GetMapping
    @Operation(description = "Find all nesting, or nesting in given year and/or nesting by given species")
    public List<NestingDto> findAllNesting(@RequestParam(required = false) Optional<Integer> year, @RequestParam(required = false) Optional<String> species) {
        return service.findAllNesting(year, species);
    }

    @GetMapping(value = "/nest-box")
    @Operation(description = "Find all nesting of a given nest box")
    public List<NestingDto> findAllNestingByNestBoxId(@RequestParam String nestBoxId) {
        return service.findAllNestingByNestBoxId(nestBoxId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponse(responseCode = "201", description = "Nesting saved successfully")
    @Operation(description = "Save a nesting and update nest box properties by field survey")
    public SurveyDto saveNestingBySurvey(@Valid @RequestBody SurveyCommand command) {
        return service.saveNestingBySurvey(command);
    }

    @PutMapping(value = "/{id}")
    @Operation(description = "Concatenate new nesting notes by nesting's database id")
    public NestingDto addNestingNotesById(@PathVariable(value= "id") long id, @Valid @RequestBody UpdateNestingCommand command) {
        return service.addNestingNotesById(id, command);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponse(responseCode = "204", description = "Nesting deleted successfully")
    @Operation(description = "Delete nesting by nesting's database id")
    public void deleteNestingById(@PathVariable(value = "id") long id) {
        service.deleteNestingById(id);
    }
}
