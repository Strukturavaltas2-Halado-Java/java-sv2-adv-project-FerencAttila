package com.training360.rollernestboxes.nestboxes.dtos;

import com.training360.rollernestboxes.nestboxes.model.Condition;
import com.training360.rollernestboxes.nestboxes.validations.ValidateNestBoxIdNotExists;
import com.training360.rollernestboxes.nestboxes.validations.ValidateValueOfEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateNestBoxConditionCommand {

    @ValidateNestBoxIdNotExists
    @Schema(description = "Unique nest box id, painted on the box", example = "1487/B")
    private String nestBoxId;

    @ValidateValueOfEnum(enumClass = Condition.class)
    @Schema(description = "Condition of the nest box", enumAsRef = true, example = "MUST_BE_REPLACED")
    private Condition condition;
}
