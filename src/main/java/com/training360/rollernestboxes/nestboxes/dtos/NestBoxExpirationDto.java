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
public class NestBoxExpirationDto {

    @Schema(description = "First known date, when the nest box was demolished")
    private LocalDate date;

    @Schema(description = "Cause of destruction", example = "It was dangerous for birds, we took it off")
    private String description;

    @Schema(description = "Reporter of expiration", example = "John Doe")
    private String reporter;
}
