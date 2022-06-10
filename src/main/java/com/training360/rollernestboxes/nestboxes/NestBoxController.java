package com.training360.rollernestboxes.nestboxes;

import com.training360.rollernestboxes.nestboxes.dtos.NestBoxDto;
import com.training360.rollernestboxes.nestboxes.dtos.NestBoxPlacementCommand;
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
@RequestMapping(value = "/api/nest-boxes")
@Tag(name = "Operations on nest boxes")
public class NestBoxController {

    private NestBoxService service;

    @GetMapping
    @Operation(description = "List all nest boxes or nest boxes in given condition.")
    public List<NestBoxDto> findNestBoxes(@RequestParam Optional<Condition> condition) {
        return service.findAllNestBoxes(condition);
    }

    @GetMapping(value = "/{nestBoxId}")
    @Operation(description = "Find the nest box by nest box id painted on the nest box")
    public NestBoxDto findNestBoxById(@PathVariable(value = "nestBoxId") String nestBoxId) {
        return service.findByNestBoxId(nestBoxId);
    }

    @GetMapping(path = "/isLiving")
    @Operation(description = "List all living nest boxes")
    public List<NestBoxDto> findAllLivingNestBoxes() {
        return service.findAllLivingNestBoxes();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponse(responseCode = "201", description = "Nest box saved successfully")
    @Operation(description = "Save a nest box into the database")
    public NestBoxDto saveNestBox(@Valid @RequestBody NestBoxPlacementCommand command) {
        return service.saveNestBox(command);
    }
}
