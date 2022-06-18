package com.training360.rollernestboxes.nestboxes.mapper;

import com.training360.rollernestboxes.nestboxes.dtos.CoordinatesDto;
import com.training360.rollernestboxes.nestboxes.dtos.NestBoxDto;
import com.training360.rollernestboxes.nestboxes.model.Coordinates;
import com.training360.rollernestboxes.nestboxes.model.NestBox;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NestBoxMapper {

    CoordinatesDto toCoordinatesDto(Coordinates coordinates);

    NestBoxDto toNestBoxDto (NestBox nestBox);
}
