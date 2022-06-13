package com.training360.rollernestboxes.nestboxes;

import com.training360.rollernestboxes.NestBoxNestingService;
import com.training360.rollernestboxes.nestboxes.dtos.ExpireNestBoxCommand;
import com.training360.rollernestboxes.nestboxes.dtos.NestBoxDto;
import com.training360.rollernestboxes.nestboxes.dtos.NestBoxPlacementCommand;
import com.training360.rollernestboxes.nestboxes.dtos.UpdateNestBoxConditionCommand;
import com.training360.rollernestboxes.nestboxes.model.Condition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/nest-box-management")
@Tag(name = "Operations on nest boxes")
public class NestBoxController {

    private NestBoxNestingService service;

    @GetMapping(value = "/nest-boxes")
    @Operation(description = "List all nest boxes or nest boxes with given list of condition.")
    public List<NestBoxDto> findNestBoxes(@RequestParam Set<Condition> conditions) {
        return service.findAllNestBoxesByConditions(conditions);
    }

    @GetMapping
    @Operation(description = "Find the nest box by nest box id painted on the nest box")
    public NestBoxDto findByNestBoxId(@RequestParam(value = "nestBoxId") String nestBoxId) {
        return service.findByNestBoxId(nestBoxId);
    }

    @GetMapping(path = "/living-nest-boxes")
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

    @PutMapping
    @Operation(description = "Update nest box condition by nest box id painted on the nest box")
    public NestBoxDto updateNestBoxConditionByNestBoxId(@RequestBody UpdateNestBoxConditionCommand command) {
        return service.updateNestBoxConditionByNestBoxId(command);
    }

    @PutMapping(value = "/expiration")
    @Operation(description = "Set a nest box as demolished")
    public NestBoxDto expireNestBox(@RequestBody ExpireNestBoxCommand command) {
        return service.expireNestBox(command);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponse(responseCode = "204", description = "Nest box deleted successfully")
    @Operation(description = "Delete nest box by nest box id painted on the nest box. Use carefully!")
    public void deleteByNestBoxId(@RequestParam(value = "nestBoxId") String nestBoxId) {
        service.deleteByNestBoxId(nestBoxId);
    }

    @DeleteMapping("/nest-boxes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponse(responseCode = "204", description = "Nest box deleted successfully")
    @Operation(description = "Delete nest box by nest box id painted on the nest box. Use carefully!")
    public void deleteAll() {
        service.deleteAllNestBoxes();
    }
}
