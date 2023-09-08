package org.defra.orchestration.mapper;

import org.defra.orchestration.apiclient.model.Commodity;
import org.defra.orchestration.dto.CertificationRequirement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CertificationRequirementMapper {

  @Mapping(target = "code", source = "id")
  @Mapping(target = "effectiveFrom", expression = "java(java.time.LocalDateTime.of(2023, 1, 1, 0, 0))")
  @Mapping(target = "effectiveTo", ignore = true)
  @Mapping(target = "certificationRequirementCode", source = "certificate.id")
  @Mapping(target = "commodityNomenclatureIdCode", source = "commodityCode.id")
  @Mapping(target = "isSelectable", constant = "true")
  @Mapping(target = "tracesIsVisible", constant = "true")
  @Mapping(target = "tracesParentCommodityCode", source = "commodityCode.parent.code")
  CertificationRequirement map(Commodity commodityCode);
}
