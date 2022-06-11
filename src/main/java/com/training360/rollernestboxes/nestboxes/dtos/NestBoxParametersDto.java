package com.training360.rollernestboxes.nestboxes.dtos;

import com.training360.rollernestboxes.nestboxes.model.NestBoxType;
import com.training360.rollernestboxes.nestboxes.model.Quarter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NestBoxParametersDto {

    @Schema(implementation = CoordinatesDto.class)
    private CoordinatesDto coordinates;

    @Schema(description = "The holder of the nest box", example = "Quercus robur")
    private String holder;

    @Schema(description = "Height of the nest box", example = "4.5")
    private double height;

    @Schema(description = "Orientation as quarter", enumAsRef = true)
    private Quarter orientation;

    @Schema(description = "Type of nest box", enumAsRef = true)
    private NestBoxType nestBoxType;
}
