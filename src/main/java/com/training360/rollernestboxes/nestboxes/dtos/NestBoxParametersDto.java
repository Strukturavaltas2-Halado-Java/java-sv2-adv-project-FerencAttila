package com.training360.rollernestboxes.nestboxes.dtos;

import com.training360.rollernestboxes.nestboxes.NestBoxType;
import com.training360.rollernestboxes.nestboxes.Quarter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NestBoxParametersDto {

    private CoordinatesDto coordinatesDto;

    private String holder;

    private double height;

    private Quarter orientation;

    private NestBoxType nestBoxType;

    private String notes;
}
