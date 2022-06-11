package com.training360.rollernestboxes.nesting;

import com.training360.rollernestboxes.nesting.dtos.MortalityDto;
import com.training360.rollernestboxes.nesting.dtos.NestingDto;
import com.training360.rollernestboxes.nesting.dtos.NestingParametersDto;
import com.training360.rollernestboxes.nesting.model.Mortality;
import com.training360.rollernestboxes.nesting.model.Nesting;
import com.training360.rollernestboxes.nesting.model.NestingParameters;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NestingMapper {

    NestingDto toNestingDto (Nesting nesting);

    NestingParametersDto toNestingParametersDto (NestingParameters nestingParameters);

    MortalityDto toMortalityDto (Mortality mortality);
}
