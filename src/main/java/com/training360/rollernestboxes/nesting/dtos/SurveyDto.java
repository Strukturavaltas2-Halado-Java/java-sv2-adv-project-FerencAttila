package com.training360.rollernestboxes.nesting.dtos;

import com.training360.rollernestboxes.nestboxes.model.Condition;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SurveyDto {

    @Schema(description = "Unique nest box id, painted on the box", example = "1487/B")
    private String nestBoxId;

    @Schema(implementation = NestingParametersDto.class)
    private NestingParametersDto nestingParameters;

    @Schema(description = "Condition of the nest box", enumAsRef = true)
    private Condition condition;

    @Schema(description = "Notes on nest box", example = "The lock of the roof must be repaired")
    private String notesOnNestBox;

    @Schema(description = "Notes on nesting", example = "On starling nest base")
    private String notesOnNesting;

    @Schema(description = "Reporter of placement", example = "John Doe")
    private String observer;
}
