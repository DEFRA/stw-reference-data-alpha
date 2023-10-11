package org.defra.orchestration.mapper;

import java.time.LocalDateTime;
import org.defra.orchestration.apiclient.model.Species;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface SpeciesMapper {

  @Mapping(target = "code", source = "species.id")
  @Mapping(target = "effectiveFrom", source = "effectiveFrom")
  @Mapping(target = "effectiveTo", source = "effectiveTo")
  @Mapping(target = "invasiveSpeciesIndicator", ignore = true)
  @Mapping(target = "speciesCode", source = "species.eppo")
  org.defra.orchestration.dto.Species map(Species species, LocalDateTime effectiveFrom,
      LocalDateTime effectiveTo);
}
