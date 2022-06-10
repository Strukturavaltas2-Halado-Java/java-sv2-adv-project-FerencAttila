package com.training360.rollernestboxes.nestboxes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class NestBoxPlacement {

    private LocalDate dateOfPlacement;

    @Embedded
    private NestBoxParameters nestBoxParameters;

    private String reporterOfPlacement;
}
