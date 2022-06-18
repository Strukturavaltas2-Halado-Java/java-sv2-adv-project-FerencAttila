package com.training360.rollernestboxes.nestboxes.dtos;

import com.training360.rollernestboxes.nestboxes.validations.beanvalidations.ValidateNestBoxIdExists;
import com.training360.rollernestboxes.nestboxes.model.Quarter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
@NoArgsConstructor
public class UpdateNestBoxCommand {

    @ValidateNestBoxIdExists
    @Schema(description = "Required, not blank, unique nest box id, painted on nest box", example = "1457/c")
    private String nestBoxNumber;

    @Schema(description = "Direction as quarter", enumAsRef = true)
    private Quarter direction;

    @Min(value = 1, message = "You cannot place a roller nest box lower then 1 meter!")
    @Max(value = 10, message ="Do not place a nest box higher then 10 meters!")
    @Schema(description = "Height of the nest box", example = "4.5")
    private double height;
}
