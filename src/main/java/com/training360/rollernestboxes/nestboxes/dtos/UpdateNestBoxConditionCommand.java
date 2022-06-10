package com.training360.rollernestboxes.nestboxes.dtos;

import com.training360.rollernestboxes.nestboxes.model.Condition;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateNestBoxConditionCommand {

    @Schema(description = "Actual condition of the nest box", enumAsRef = true)
    @NotBlank
    private Condition condition;
}
