package org.defra.orchestration.mapper;

import org.defra.orchestration.apiclient.model.Commodity;
import org.defra.orchestration.dto.CertificationRequirement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper
public interface CertificationRequirementMapper {

  @Mapping(target = "code", source = ".", qualifiedByName = "getId")
  @Mapping(target = "effectiveFrom", expression = "java(java.time.LocalDateTime.of(2023, 1, 1, 0, 0))")
  @Mapping(target = "effectiveTo", ignore = true)
  @Mapping(target = "certificationRequirementCode", source = "certificate.id")
  @Mapping(target = "commodityNomenclatureIdCode", source = "commodityCode.id")
  @Mapping(target = "isSelectable", constant = "true")
  @Mapping(target = "tracesIsVisible", constant = "true")
  @Mapping(target = "tracesParentCommodityCode", source = "commodityCode.parent.code", qualifiedByName = "trimCommodityCode")
  CertificationRequirement map(Commodity commodity);

  @Named("getId")
  default String getId(Commodity commodity) {
    return commodity.getCommodityCode().getId() + commodity.getCertificate().getId();
  }

  @Named("trimCommodityCode")
  default String trimCommodityCode(String code) {
    if (code == null) {
      return null;
    }
    return code.replaceAll("(00)*$", "");
  }
}
