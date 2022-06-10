package com.training360.rollernestboxes.nestboxes.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NestBoxExpirationDto {

    private LocalDate date;

    private String description;

    private String reporter;
}
