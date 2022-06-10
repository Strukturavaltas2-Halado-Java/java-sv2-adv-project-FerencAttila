package com.training360.rollernestboxes.nestboxes.dtos;

import com.training360.rollernestboxes.nestboxes.Condition;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NestBoxDto {

    private Long id;

    private String nestBoxId;

    private NestBoxPlacementDto nestBoxPlacementDto;

    private NestBoxExpirationDto nestBoxExpirationDto;

    private Condition condition;
}
