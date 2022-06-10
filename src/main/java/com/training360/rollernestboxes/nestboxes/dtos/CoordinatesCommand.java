package com.training360.rollernestboxes.nestboxes.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoordinatesCommand {

    private static final int MINIMUM_EOV_X = 426400;
    private static final int MAXIMUM_EOV_X = 937500;
    private static final int MINIMUM_EOV_Y = 43000;
    private static final int MAXIMUM_EOV_Y = 361400;

    private double eovX;

    private double eovY;
}
