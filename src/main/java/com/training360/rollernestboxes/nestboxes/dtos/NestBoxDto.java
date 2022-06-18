package com.training360.rollernestboxes.nestboxes.dtos;

import com.training360.rollernestboxes.nestboxes.model.Quarter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NestBoxDto {

    @Schema(description = "Nest box id, painted on nest box", example = "1457/c")
    private String nestBoxNumber;

    @Schema(implementation = CoordinatesDto.class)
    private CoordinatesDto coordinates;

    @Schema(description = "Direction as quarter", enumAsRef = true)
    private Quarter direction;

    @Schema(description = "Height of the nest box", example = "4.5")
    private double height;
}
