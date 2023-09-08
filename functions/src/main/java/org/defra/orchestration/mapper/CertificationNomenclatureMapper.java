package org.defra.orchestration.mapper;

import org.defra.orchestration.apiclient.model.Commodity;
import org.defra.orchestration.dto.CertificationNomenclature;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CertificationNomenclatureMapper {

  @Mapping(target = "code", source = "id")
  @Mapping(target = "effectiveFrom", expression = "java(java.time.LocalDateTime.of(2023, 1, 1, 0, 0))")
  @Mapping(target = "effectiveTo", ignore = true)
  @Mapping(target = "certificationRequirementCode", source = "id")
  @Mapping(target = "classCode", ignore = true)
  @Mapping(target = "classId", ignore = true)
  @Mapping(target = "className", source = "species.clazz")
  @Mapping(target = "commodityTypeCode", ignore = true)
  @Mapping(target = "commodityTypeId", ignore = true)
  @Mapping(target = "commodityTypeName", ignore = true)
  @Mapping(target = "familyCode", ignore = true)
  @Mapping(target = "familyId", ignore = true)
  @Mapping(target = "familyName", source = "species.family")
  @Mapping(target = "isDefaultClass", ignore = true)
  @Mapping(target = "isDefaultCommodityType", ignore = true)
  @Mapping(target = "isDefaultFamily", ignore = true)
  @Mapping(target = "isDefaultSpecies", ignore = true)
  @Mapping(target = "speciesCode", source = "species.id")
  @Mapping(target = "speciesId", source = "species.id")
  @Mapping(target = "speciesName", source = "species.species")
  CertificationNomenclature map(Commodity commodity);
}
