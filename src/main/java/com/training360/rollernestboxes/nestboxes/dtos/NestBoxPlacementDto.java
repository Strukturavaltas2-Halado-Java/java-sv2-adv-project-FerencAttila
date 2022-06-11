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

    @Schema(description = "Date of placement")
    private LocalDate dateOfPlacement;

    @Schema(implementation = NestBoxParametersDto.class)
    private NestBoxParametersDto nestBoxParameters;

    @Schema(description = "Reporter of placement", example = "John Doe")
    private String reporterOfPlacement;
}
