package com.training360.rollernestboxes.nestboxes.dtos;

import com.training360.rollernestboxes.nestboxes.NestBoxType;
import com.training360.rollernestboxes.nestboxes.Quarter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NestBoxParametersCommand {

    private CoordinatesCommand coordinatesCommand;

    private String holder;

    private double height;

    private Quarter orientation;

    private NestBoxType nestBoxType;

    private String notes;
}
