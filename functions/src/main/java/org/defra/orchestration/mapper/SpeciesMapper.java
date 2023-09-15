package org.defra.orchestration.mapper;

import org.defra.orchestration.apiclient.model.Commodity;
import org.defra.orchestration.apiclient.model.Species;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface SpeciesMapper {

  @Mapping(target = "code", source = "species.id")
  @Mapping(target = "effectiveFrom", source = "commodity.effectiveFrom")
  @Mapping(target = "effectiveTo", source = "commodity.effectiveTo")
  @Mapping(target = "invasiveSpeciesIndicator", ignore = true)
  @Mapping(target = "speciesCode", source = "species.eppo")
  org.defra.orchestration.dto.Species map(Species species, Commodity commodity);
}
