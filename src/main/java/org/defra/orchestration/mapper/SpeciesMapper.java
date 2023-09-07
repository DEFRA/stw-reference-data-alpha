package org.defra.orchestration.mapper;

import org.defra.orchestration.apiclient.model.Species;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface SpeciesMapper {

  @Mapping(target = "code", source = "id")
  @Mapping(target = "effectiveFrom", expression = "java(java.time.LocalDateTime.of(2023, 1, 1, 0, 0))")
  @Mapping(target = "effectiveTo", ignore = true)
  @Mapping(target = "invasiveSpeciesIndicator", ignore = true)
  @Mapping(target = "speciesCode", source = "eppo")
  org.defra.orchestration.dto.Species map(Species species);
}
