package com.training360.rollernestboxes.nestboxes.dtos;

import com.training360.rollernestboxes.nestboxes.validations.ValidateNestBoxId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class ExpireNestBoxCommand {

    @Schema(description = "Unique nest box id, painted on the box", example = "1487/B")
    @NotBlank(message = "Nest box id cannot be null or empty!")
    @ValidateNestBoxId
    private String nestBoxId;

    @Schema(description = "First known date, when the nest box was demolished")
    @PastOrPresent(message = "Date cannot be in the future!")
    private LocalDate date;

    @Schema(description = "Cause of destruction", example = "It was dangerous for birds, we took it off")
    private String description;

    @Schema(description = "Reporter of expiration", example = "John Doe")
    @Size(min = 6, max = 100, message = "Person name must be consist of minimum 6 and maximum 100 characters!")
    private String reporter;

}
