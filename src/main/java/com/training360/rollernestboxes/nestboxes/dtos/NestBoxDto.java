package com.training360.rollernestboxes.nestboxes.dtos;

import com.training360.rollernestboxes.nestboxes.model.Condition;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NestBoxDto {

    @Schema(description = "Database id")
    private Long id;

    @Schema(description = "Unique Nest box id, painted on the box", example = "1487/B")
    private String nestBoxId;

    @Schema(implementation = NestBoxPlacementDto.class)
    private NestBoxPlacementDto nestBoxPlacement;

    @Schema(implementation = NestBoxExpirationDto.class)
    private NestBoxExpirationDto nestBoxExpiration;

    @Schema(description = "Actual condition of the nest box", enumAsRef = true)
    private Condition condition;

    @Schema(description = "Notes on nest box", example = "In the same tree as the expired 254 nest box")
    private String notes;
}
