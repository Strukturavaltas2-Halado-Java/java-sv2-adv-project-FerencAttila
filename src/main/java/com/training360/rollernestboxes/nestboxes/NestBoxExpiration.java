package com.training360.rollernestboxes.nestboxes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class NestBoxExpiration {

    private String dateOfExpiry;

    private String descriptionOfExpiry;

    private String reporterOfExpiry;
}
