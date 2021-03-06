package com.training360.rollernestboxes.nest.dtos;

import com.training360.rollernestboxes.nestboxes.dtos.CoordinatesCommand;
import com.training360.rollernestboxes.nestboxes.dtos.CoordinatesDto;
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
public class ZoologyDataDto {

    @Schema(description = "Date of observation")
    private LocalDate dateOfSurvey;

    @Schema(implementation = CoordinatesCommand.class)
    private CoordinatesDto coordinates;

    @Schema(description = "Scientific name of nesting species", example = "Coracias garrulus")
    private String species;

    @Schema(description = "Quantity of animals", example = "3")
    private int quantity;

    @Schema(description = "Unit of quantity", example = "individual")
    private String unitOfQuantity;

    @Schema(description = "Activity of animals", example = "Nestling in nest")
    private String activity;

    @Schema(description = "Survey methodology", example = "Nest box control")
    private String surveyMethod;

    @Schema(description = "How the data collected", example = "visual")
    private String collectionMethod;

    @Schema(description = "Observer", example = "John Doe")
    private String observer;
}
