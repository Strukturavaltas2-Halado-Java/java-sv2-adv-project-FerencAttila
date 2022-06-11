package com.training360.rollernestboxes.nesting.dtos;

import com.training360.rollernestboxes.nestboxes.dtos.CoordinatesCommand;
import com.training360.rollernestboxes.nestboxes.model.Condition;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SurveyCommand {

    public static final int MAXIMUM_NUMBER_OF_SUCCESSORS = 20;

    @Schema(description = "Unique nest box id, painted on the box", example = "1487/B")
    private String nestBoxId;

    @Schema(implementation = CoordinatesCommand.class)
    private CoordinatesCommand coordinatesCommand;

    @Schema(implementation = NestingParametersCommand.class)
    private NestingParametersCommand nestingParametersCommand;

    @Schema(description = "Condition of the nest box", enumAsRef = true)
    private Condition condition;

    @Schema(description = "Notes on nest box", example = "The lock of the roof must be repaired")
    private String notesOnNestBox;

    @Schema(description = "Reporter of placement", example = "John Doe")
    @Size(min = 6, max = 100, message = "Person name must be consist of minimum 6 and maximum 100 characters!")
    private String observer;
}
