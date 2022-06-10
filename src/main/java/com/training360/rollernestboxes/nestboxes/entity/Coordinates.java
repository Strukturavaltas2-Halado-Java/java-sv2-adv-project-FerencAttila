package com.training360.rollernestboxes.nestboxes.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Coordinates {

    private int eovX;

    private int eovY;
}
