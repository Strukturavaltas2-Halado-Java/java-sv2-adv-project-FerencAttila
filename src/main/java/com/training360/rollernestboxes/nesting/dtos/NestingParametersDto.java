package com.training360.rollernestboxes.nesting.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NestingParametersDto {

    @Schema(description = "Date of observation")
    private LocalDate dateOfSurvey;

    @Schema(description = "Scientific name of nesting species", example = "Coracias garrulus")
    private String nestingSpecies;

    @Schema(description = "Number of living eggs", example = "4")
    private int numberOfEggs;

    @Schema(description = "Number of living nestlings", example = "5")
    private int numberOfNestlings;

    @Schema(description = "Description of age", example = "18-20 days old chicks")
    private String ageOfNestlings;

    @Schema(implementation = MortalityDto.class)
    private MortalityDto mortality;
}
