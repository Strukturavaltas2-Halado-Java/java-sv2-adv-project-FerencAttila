package com.training360.rollernestboxes.nestboxes.dtos;

import com.training360.rollernestboxes.nestboxes.NestBoxType;
import com.training360.rollernestboxes.nestboxes.Quarter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NestBoxParametersCommand {

    @Schema(implementation = CoordinatesCommand.class)
    private CoordinatesCommand coordinatesCommand;

    @Schema(description = "The holder of the nest box", example = "Quercus robur")
    private String holder;

    @Schema(description = "Height of the nest box", example = "4.5")
    @Min(1)
    private double height;

    @Schema(description = "Orientation as quarter", enumAsRef = true)
    private Quarter orientation;

    @Schema(description = "Type of nest box", enumAsRef = true)
    private NestBoxType nestBoxType;

    @Schema(description = "Notes on nest box", example = "In the same tree as the expired 254 nest box")
    private String notes;
}
