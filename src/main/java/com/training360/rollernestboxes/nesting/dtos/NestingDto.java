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
public class NestingDto {

    @Schema(description = "Database id")
    private Long id;

    @Schema(description = "Unique Nest box id, painted on the box", example = "1487/B")
    private String nestBoxId;

    @Schema(implementation = NestingParametersDto.class)
    private NestingParametersDto nestingParameters;

    @Schema(description = "Notes on nesting", example = "On starling nest base")
    private String notes;

    @Schema(description = "Reporter of nesting", example = "John Doe")
    private String observer;
}
