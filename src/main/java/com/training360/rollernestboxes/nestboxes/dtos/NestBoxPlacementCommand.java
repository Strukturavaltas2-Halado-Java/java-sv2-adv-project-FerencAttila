package com.training360.rollernestboxes.nestboxes.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NestBoxPlacementCommand {

    private String nestBoxId;

    private LocalDate dateOfPlacement;

    private NestBoxParametersCommand nestBoxParametersCommand;

    private String reporterOfPlacement;
}
