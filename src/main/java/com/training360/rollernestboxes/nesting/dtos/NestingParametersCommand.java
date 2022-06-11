package com.training360.rollernestboxes.nesting.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NestingParametersCommand {

    @PastOrPresent
    @Schema(description = "Date of observation")
    private LocalDate dateOfSurvey;

    @Schema(description = "Scientific name of nesting species", example = "Coracias garrulus")
    private String nestingSpecies;

    @Schema(description = "Number of living eggs", example = "4")
    @PositiveOrZero(message = "Number of eggs must be positive integer or zero!")
    @Max(value = SurveyCommand.MAXIMUM_NUMBER_OF_SUCCESSORS, message = "Number of eggs must be less then 21!")
    private int numberOfEggs;

    @Schema(description = "Number of living nestlings", example = "5")
    @PositiveOrZero(message = "Number of nestlings must be positive integer or zero!")
    @Max(value = SurveyCommand.MAXIMUM_NUMBER_OF_SUCCESSORS, message = "Number of nestlings must be less then 21!")
    private int numberOfNestlings;

    @Schema(description = "Description of age", example = "18-20 days old chicks")
    @Size(max = 255, message = "Description of age of nestling must be consists maximum of 255 characters!")
    private String ageOfNestlings;

    @Schema(implementation = MortalityCommand.class)
    private MortalityCommand mortality;
}
