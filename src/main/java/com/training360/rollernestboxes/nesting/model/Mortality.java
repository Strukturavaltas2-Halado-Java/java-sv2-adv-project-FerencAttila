package com.training360.rollernestboxes.nesting.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Mortality {

    private int numberOfScrambledEggs;

    private int numberOfDeadNestlings;

    private int numberOfDeadAdults;

    private String description;
}