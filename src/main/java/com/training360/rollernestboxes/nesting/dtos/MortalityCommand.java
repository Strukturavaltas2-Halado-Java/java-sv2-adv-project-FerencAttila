package com.training360.rollernestboxes.nesting.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MortalityCommand {

    @Schema(description = "Number of scrambled and/or cracked eggs", example = "1")
    @PositiveOrZero(message = "Number of scrambled or cracked eggs must be positive integer or zero!")
    @Max(value = SurveyCommand.MAXIMUM_NUMBER_OF_SUCCESSORS, message = "Number of scrambled or cracked eggs must be less then 21!")
    private int numberOfScrambledEggs;

    @Schema(description = "Number of dead nestlings", example = "0")
    @PositiveOrZero(message = "Number of dead nestlings must be positive integer or zero!")
    @Max(value = SurveyCommand.MAXIMUM_NUMBER_OF_SUCCESSORS, message = "Number of dead nestlings must be less then 21!")
    private int numberOfDeadNestlings;

    @Schema(description = "Number of dead adults", example = "1")
    @PositiveOrZero(message = "Number of dead adults must be positive or zero!")
    private int numberOfDeadAdults;

    @Schema(description = "Causes and/or circumstances of mortality", example = "Predation, possibly by marten")
    private String description;
}
