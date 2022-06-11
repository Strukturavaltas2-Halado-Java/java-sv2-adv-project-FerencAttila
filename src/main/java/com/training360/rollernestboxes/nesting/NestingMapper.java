package com.training360.rollernestboxes.nesting;

import com.training360.rollernestboxes.nestboxes.model.NestBox;
import com.training360.rollernestboxes.nesting.dtos.*;
import com.training360.rollernestboxes.nesting.model.Mortality;
import com.training360.rollernestboxes.nesting.model.Nesting;
import com.training360.rollernestboxes.nesting.model.NestingParameters;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NestingMapper {

    MortalityDto toMortalityDto(Mortality mortality);

    Mortality toMortality(MortalityCommand mortalityCommand);

    NestingParametersDto toNestingParametersDto(NestingParameters nestingParameters);

    NestingParameters toNestingParameters(NestingParametersCommand nestingParametersCommand);

    SurveyDto toSurveyDto(NestBox nestBox, Nesting nesting);

    @Mapping(target = "nestBoxId", source = "nestBox.nestBoxId")
    NestingDto toNestingDto(Nesting nesting);
}
