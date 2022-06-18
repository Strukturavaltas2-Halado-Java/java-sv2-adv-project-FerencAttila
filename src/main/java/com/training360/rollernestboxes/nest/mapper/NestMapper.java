package com.training360.rollernestboxes.nest.mapper;

import com.training360.rollernestboxes.nest.dtos.NestDto;
import com.training360.rollernestboxes.nest.model.Nest;
import com.training360.rollernestboxes.nestboxes.dtos.CoordinatesDto;
import com.training360.rollernestboxes.nest.dtos.ZoologyDataDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NestMapper {

    @Mapping(target = "nestBoxNumber", source = "nestBox.nestBoxNumber")
    @Mapping(target = "coordinates", source = "nestBox.coordinates")
    NestDto toNestDto(Nest nest);

    default ZoologyDataDto toZoologyDataDto(Nest nest) {
        return new ZoologyDataDto(nest.getDateOfSurvey(),
                new CoordinatesDto(nest.getNestBox().getCoordinates().getEovX(),
                        nest.getNestBox().getCoordinates().getEovY()),
                nest.getSpecies(),
                nest.getNumberOfNestlings(),
                "individual",
                "Nestling in nest",
                "Nest box control",
                "visual",
                nest.getObserver());
    }
}
