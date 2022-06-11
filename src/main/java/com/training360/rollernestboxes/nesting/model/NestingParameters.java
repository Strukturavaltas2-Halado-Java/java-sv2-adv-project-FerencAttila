package com.training360.rollernestboxes.nesting.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class NestingParameters {

    @PastOrPresent
    private LocalDate dateOfSurvey;

    private String nestingSpecies;

    private int numberOfEggs;

    private int numberOfNestlings;

    private String ageOfNestlings;

    @Embedded
    private Mortality mortality;
}