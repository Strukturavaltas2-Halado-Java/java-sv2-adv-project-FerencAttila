package com.training360.rollernestboxes.nestboxes.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NestBoxPlacementDto {

    @Schema(description = "Unique nest box id, painted on the box", example = "1487/B")
    private String nestBoxId;

    @Schema(description = "Date of placement")
    private LocalDate dateOfPlacement;

    @Schema(implementation = NestBoxParametersDto.class)
    private NestBoxParametersDto nestBoxParametersDto;

    @Schema(description = "Notes on nest box", example = "In the same tree as the expired 254 nest box")
    private String notes;

    @Schema(description = "Reporter of placement", example = "John Doe")
    private String reporterOfPlacement;
}
