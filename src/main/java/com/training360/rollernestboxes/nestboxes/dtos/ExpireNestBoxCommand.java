package com.training360.rollernestboxes.nestboxes.dtos;

import com.training360.rollernestboxes.nestboxes.validations.ValidateNestBoxIdNotExists;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpireNestBoxCommand {

    @ValidateNestBoxIdNotExists
    @Schema(description = "Unique nest box id, painted on the box", example = "1487/B")
    private String nestBoxId;

    @PastOrPresent(message = "Date of expiry cannot be in the future!")
    @NotNull(message = "Date of expiry cannot be null!")
    @Schema(description = "First known date, when the nest box was demolished")
    private LocalDate dateOfExpiry;

    @Size(max = 65535, message = "Expiration description must be consist of maximum 65535 characters!")
    @Schema(description = "Cause of destruction", example = "It was dangerous for birds, we took it off")
    private String descriptionOfExpiry;

    @Size(min = 6, max = 100, message = "Person name must be consist of minimum 6 and maximum 100 characters!")
    @NotBlank(message = "Reporter of expiration cannot be blank!")
    @Schema(description = "Reporter of expiration", example = "John Doe")
    private String reporterOfExpiry;
}
