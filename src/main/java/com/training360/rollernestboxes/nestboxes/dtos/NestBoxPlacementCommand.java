package com.training360.rollernestboxes.nestboxes.dtos;

import com.training360.rollernestboxes.nestboxes.validations.ValidateNestBoxIdNotExists;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NestBoxPlacementCommand {

    @Schema(description = "Unique nest box id, painted on the box", example = "1487/B")
    @ValidateNestBoxIdNotExists
    private String nestBoxId;

    @Schema(description = "Date of placement")
    @PastOrPresent(message = "Date cannot be in the future!")
    private LocalDate dateOfPlacement;

    @Schema(implementation = NestBoxParametersCommand.class)
    private NestBoxParametersCommand nestBoxParametersCommand;

    @Schema(description = "Reporter of placement", example = "John Doe")
    @Size(min = 6, max = 100, message = "Person name must be consist of minimum 6 and maximum 100 characters!")
    private String reporterOfPlacement;
}
