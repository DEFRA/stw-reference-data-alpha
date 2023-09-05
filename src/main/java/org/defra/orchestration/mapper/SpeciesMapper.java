package org.defra.orchestration.mapper;

import org.defra.orchestration.apiclient.model.Species;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface SpeciesMapper {

  @Mapping(target = "code", source = "id")
  @Mapping(target = "effectiveFrom", ignore = true)
  @Mapping(target = "effectiveTo", ignore = true)
  @Mapping(target = "eppoCode", source = "eppo")
  org.defra.orchestration.dto.Species map(Species species);
}
