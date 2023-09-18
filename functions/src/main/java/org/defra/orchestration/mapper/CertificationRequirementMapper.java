package org.defra.orchestration.mapper;

import org.defra.orchestration.apiclient.model.Commodity;
import org.defra.orchestration.apiclient.model.CommodityCode;
import org.defra.orchestration.dto.CertificationRequirement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper
public interface CertificationRequirementMapper {

  @Mapping(target = "code", source = ".", qualifiedByName = "requirementCode")
  @Mapping(target = "effectiveFrom", source = "effectiveFrom", defaultValue = "1970-01-01T00:00:00")
  @Mapping(target = "effectiveTo", source = "effectiveTo")
  @Mapping(target = "certificationRequirementCode", source = "certificate.id")
  @Mapping(target = "commodityNomenclatureIdCode", source = "commodityCode.id")
  @Mapping(target = "isSelectable", source = ".", qualifiedByName = "selectable")
  @Mapping(target = "tracesIsVisible", constant = "true")
  @Mapping(target = "tracesParentCommodityCode", source = "commodityCode.parent", qualifiedByName = "commodityCode")
  CertificationRequirement map(Commodity commodity);

  @Named("selectable")
  default Boolean selectable(Commodity commodity) {
    return commodity.getSpecies() != null;
  }

  @Named("requirementCode")
  default String requirementCode(Commodity commodity) {
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
