package com.training360.rollernestboxes.nestboxes.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CoordinatesCommand {

    private static final int MINIMUM_EOV_X = 426400;
    private static final int MAXIMUM_EOV_X = 937500;
    private static final int MINIMUM_EOV_Y = 43000;
    private static final int MAXIMUM_EOV_Y = 361400;

    @Min(value = MINIMUM_EOV_X, message = "The minimum value of the x coordinate is " + MINIMUM_EOV_X)
    @Max(value = MAXIMUM_EOV_X, message = "The maximum value of the x coordinate is " + MAXIMUM_EOV_X)
    @NotNull(message = "X coordinate is required")
    @Schema(description = "X coordinate in HD72/EOV (EPSG:23700) projection", example = "752577")
    private int eovX;

    @Min(value = MINIMUM_EOV_Y, message = "The minimum value of the y coordinate is " + MINIMUM_EOV_Y)
    @Max(value = MAXIMUM_EOV_Y, message = "The maximum value of the y coordinate is " + MAXIMUM_EOV_Y)
    @NotNull(message = "Y coordinate is required")
    @Schema(description = "Y coordinate in HD72/EOV (EPSG:23700) projection", example = "268376")
    private int eovY;
}
