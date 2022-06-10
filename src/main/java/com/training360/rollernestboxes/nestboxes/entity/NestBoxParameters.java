package com.training360.rollernestboxes.nestboxes.entity;

import com.training360.rollernestboxes.nestboxes.NestBoxType;
import com.training360.rollernestboxes.nestboxes.Quarter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class NestBoxParameters {

    @Embedded
    private Coordinates coordinates;

    private String holder;

    private double height;

    @Enumerated(value = EnumType.STRING)
    private Quarter orientation;

    @Enumerated(value = EnumType.STRING)
    private NestBoxType nestBoxType;
}
