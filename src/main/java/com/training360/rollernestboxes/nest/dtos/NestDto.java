package com.training360.rollernestboxes.nest.dtos;

import com.training360.rollernestboxes.nestboxes.dtos.CoordinatesDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class NestDto {

    @Schema(description = "Nest box id, painted on nest box", example = "1457/c")
    private String nestBoxNumber;

    @Schema(implementation = CoordinatesDto.class)
    private CoordinatesDto coordinates;

    @Schema(description = "Date of observation")
    private LocalDate dateOfSurvey;

    @Schema(description = "Scientific name of nesting species", example = "Coracias garrulus")
    private String species;

    @Schema(description = "Sum of nestlings and/or eggs", example = "4")
    private int numberOfNestlings;

    @Schema(description = "Reporter of placement", example = "John Doe")
    private String observer;
}
