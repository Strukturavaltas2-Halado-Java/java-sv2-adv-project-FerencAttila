package com.training360.rollernestboxes.nesting.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MortalityDto {

    @Schema(description = "Number of scrambled and/or cracked eggs", example = "1")
    private int numberOfScrambledEggs;

    @Schema(description = "Number of dead nestlings", example = "0")
    private int numberOfDeadNestlings;

    @Schema(description = "Number of dead adults", example = "1")
    private int numberOfDeadAdults;

    @Schema(description = "Causes and/or circumstances of mortality", example = "Predation, possibly by marten")
    private String description;
}
