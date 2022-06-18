package com.training360.rollernestboxes.nestboxes.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CoordinatesDto {

    @Schema(description = "X coordinate in HD72/EOV (EPSG:23700) projection", example = "752577")
    private int eovX;

    @Schema(description = "Y coordinate in HD72/EOV (EPSG:23700) projection", example = "268376")
    private int eovY;
}
