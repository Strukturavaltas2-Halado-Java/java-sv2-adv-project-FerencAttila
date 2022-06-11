package com.training360.rollernestboxes.nestboxes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class NestBoxExpiration {

    private LocalDate dateOfExpiry;

    private String descriptionOfExpiry;

    private String reporterOfExpiry;
}
