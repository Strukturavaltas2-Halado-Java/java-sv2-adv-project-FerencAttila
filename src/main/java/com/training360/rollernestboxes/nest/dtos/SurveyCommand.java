package com.training360.rollernestboxes.nest.dtos;

import com.training360.rollernestboxes.nestboxes.validations.beanvalidations.ValidateNestBoxIdExists;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SurveyCommand {

    @ValidateNestBoxIdExists
    @Schema(description = "Required nest box id, painted on nest box", example = "1457/c")
    private String nestBoxNumber;

    @PastOrPresent(message = "Date of observation cannot be in the future!")
    @NotNull(message = "Date of observation cannot be null!")
    @Schema(description = "Date of observation")
    private LocalDate dateOfSurvey;

    @Size(min = 5, max = 45, message = "Scientific name of nestig species must be consist of minimum 5 and maximum of 45 characters!")
    @Schema(description = "Scientific name of nesting species", example = "Coracias garrulus")
    private String species;

    @PositiveOrZero(message = "Number of nestlings must be positive integer or zero!")
    @Max(value = 20, message = "Number of nestlings must be less or equal to 20!")
    @Schema(description = "Sum of nestlings and/or eggs", example = "4")
    private int numberOfNestlings;

    @NotBlank(message = "Name of observer cannot be blank!")
    @Size(min = 5, max = 100, message = "Person name must be consist of minimum 5 and maximum of 100 characters!")
    @Schema(description = "Reporter of placement", example = "John Doe")
    private String observer;
}
