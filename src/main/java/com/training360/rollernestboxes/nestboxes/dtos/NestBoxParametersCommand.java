package com.training360.rollernestboxes.nestboxes.dtos;

import com.training360.rollernestboxes.nestboxes.model.NestBoxType;
import com.training360.rollernestboxes.nestboxes.model.Quarter;
import com.training360.rollernestboxes.nestboxes.validations.ValidateValueOfEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NestBoxParametersCommand {

    @Schema(implementation = CoordinatesCommand.class)
    private CoordinatesCommand coordinatesCommand;

    @Schema(description = "The holder of the nest box", example = "Quercus robur")
    @Size(max = 100, message = "Holder value must be consits of maximum 100 characters!")
    private String holder;

    @Schema(description = "Height of the nest box", example = "4.5")
    @Min(value = 1, message = "You cannot place a roller nest box under 1 meter height!")
    private double height;

    @Schema(description = "Orientation as quarter", enumAsRef = true)
    @ValidateValueOfEnum(enumClass = Quarter.class)
    private Quarter orientation;

    @ValidateValueOfEnum(enumClass = NestBoxType.class)
    @Schema(description = "Type of nest box", enumAsRef = true)
    private NestBoxType nestBoxType;

    @Schema(description = "Notes on nest box", example = "In the same tree as the expired 254 nest box")
    private String notes;
}
