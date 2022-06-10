package com.training360.rollernestboxes.nestboxes;

import com.training360.rollernestboxes.nestboxes.dtos.*;
import com.training360.rollernestboxes.nestboxes.model.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NestBoxMapper {

    CoordinatesDto toCoordinatesDto(Coordinates coordinates);

    NestBoxDto toNestBoxDto(NestBox nestBox);

    NestBoxExpirationDto toNestBoxExpirationDto(NestBoxExpiration nestBoxExpiration);

    NestBoxParametersDto toNestBoxParametersDto(NestBoxParameters nestBoxParameters);

    NestBoxPlacementDto toNestBoxPlacementDto(NestBoxPlacement nestBoxPlacement);
}
