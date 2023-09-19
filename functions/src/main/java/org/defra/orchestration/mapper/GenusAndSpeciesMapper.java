package org.defra.orchestration.mapper;

import java.util.List;
import org.defra.orchestration.apiclient.model.Species;
import org.defra.orchestration.dto.CommonName;
import org.defra.orchestration.dto.GenusAndSpecies;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper
public interface GenusAndSpeciesMapper {

  @Mapping(target = "codeId", source = "id")
  @Mapping(target = "eppoCode", source = "eppo")
  @Mapping(target = "dataGroup", ignore = true)
  @Mapping(target = "taxonomicLevel", source = "rank")
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "modifiedAt", ignore = true)
  @Mapping(target = "preferredName", source = "species")
  @Mapping(target = "codeLanguage", constant = "la")
  @Mapping(target = "commonNames", source = "simpleName", qualifiedByName = "commonNames")
  GenusAndSpecies map(Species species);

  @Named("commonNames")
  default List<CommonName> commonNames(String simpleName) {
    if (simpleName == null) {
      return List.of();
    } else {
      return List.of(CommonName.builder()
          .fullName(simpleName)
          .codeLanguage("en")
          .build());
    }
  }
}
