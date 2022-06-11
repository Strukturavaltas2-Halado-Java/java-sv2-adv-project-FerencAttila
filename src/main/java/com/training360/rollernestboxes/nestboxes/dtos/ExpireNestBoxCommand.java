package com.training360.rollernestboxes.nestboxes.dtos;

import com.training360.rollernestboxes.nestboxes.validations.ValidateNestBoxIdNotExists;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpireNestBoxCommand {

    @Schema(description = "Unique nest box id, painted on the box", example = "1487/B")
    @ValidateNestBoxIdNotExists
    private String nestBoxId;

    @Schema(description = "First known date, when the nest box was demolished")
    @PastOrPresent(message = "Date cannot be in the future!")
    @NotNull
    private LocalDate dateOfExpiry;

    @Schema(description = "Cause of destruction", example = "It was dangerous for birds, we took it off")
    @Size(max = 65535, message = "Expiration description must be consist of maximum 65535 characters!")
    private String descriptionOfExpiry;

    @Schema(description = "Reporter of expiration", example = "John Doe")
    @Size(min = 6, max = 100, message = "Person name must be consist of minimum 6 and maximum 100 characters!")
    @NotNull
    private String reporterOfExpiry;
}
