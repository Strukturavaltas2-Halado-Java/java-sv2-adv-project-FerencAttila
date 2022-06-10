package com.training360.rollernestboxes.nestboxes.dtos;

import com.training360.rollernestboxes.nestboxes.validations.NestBoxId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NestBoxPlacementCommand {

    @Schema(description = "Unique nest box id, painted on the box", example = "1487/B")
    @NotBlank
    @NestBoxId
    private String nestBoxId;

    @Schema(description = "Date of placement")
    @PastOrPresent
    private LocalDate dateOfPlacement;

    @Schema(implementation = NestBoxParametersCommand.class)
    private NestBoxParametersCommand nestBoxParametersCommand;

    @Schema(description = "Reporter of placement", example = "John Doe")
    private String reporterOfPlacement;
}
