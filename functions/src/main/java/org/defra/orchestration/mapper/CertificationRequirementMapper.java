package org.defra.orchestration.mapper;

import org.defra.orchestration.apiclient.model.Commodity;
import org.defra.orchestration.apiclient.model.CommodityCode;
import org.defra.orchestration.dto.CertificationRequirement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper
public interface CertificationRequirementMapper {

  @Mapping(target = "code", source = ".", qualifiedByName = "code")
  @Mapping(target = "effectiveFrom", expression = "java(java.time.LocalDateTime.of(2023, 1, 1, 0, 0))")
  @Mapping(target = "effectiveTo", ignore = true)
  @Mapping(target = "certificationRequirementCode", source = "certificate.id")
  @Mapping(target = "commodityNomenclatureIdCode", source = "commodityCode.id")
  @Mapping(target = "isSelectable", constant = "true")
  @Mapping(target = "tracesIsVisible", constant = "true")
  @Mapping(target = "tracesParentCommodityCode", source = "commodityCode.parent", qualifiedByName = "commodityCode")
  CertificationRequirement map(Commodity commodity);

  @Named("code")
  default String code(Commodity commodity) {
    return commodity.getCertificate().getId() + "-" + commodity.getCommodityCode().getId();
  }

  @Named("commodityCode")
  default String commodityCode(CommodityCode commodityCode) {
    if (commodityCode == null) {
      return null;
    }
    String trimmed = commodityCode.getCode().replaceAll("(00)*$", "");
    return commodityCode.getSuffix().equals("80")
        ? trimmed
        : trimmed.substring(0, trimmed.length() - 1);
  }
}
