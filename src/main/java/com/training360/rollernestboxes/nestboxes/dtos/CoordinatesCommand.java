package com.training360.rollernestboxes.nestboxes.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoordinatesCommand {

    private static final int MINIMUM_EOV_X = 426400;
    private static final int MAXIMUM_EOV_X = 937500;
    private static final int MINIMUM_EOV_Y = 43000;
    private static final int MAXIMUM_EOV_Y = 361400;

    @Schema(description = "X coordinate in HD72/EOV (EPSG:23700) projection", example = "752577.15")
    @Min(value = MINIMUM_EOV_X, message = "The minimum value of the X coordinate is " + MINIMUM_EOV_X)
    @Max(value = MAXIMUM_EOV_X, message = "The maximum value of the X coordinate is " + MAXIMUM_EOV_X)
    private double eovX;

    @Schema(description = "Y coordinate in HD72/EOV (EPSG:23700) projection", example = "268376.08")
    @Min(value = MINIMUM_EOV_Y, message = "The minimum value of the Y coordinate is " + MINIMUM_EOV_Y)
    @Max(value = MAXIMUM_EOV_Y, message = "The maximum value of the Y coordinate is " + MAXIMUM_EOV_Y)
    private double eovY;
}
