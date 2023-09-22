package org.defra.orchestration.mapper;

import org.defra.orchestration.apiclient.model.Commodity;
import org.defra.orchestration.dto.CertificationRequirement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(uses = MapperUtils.class)
public interface CertificationRequirementMapper {

  @Mapping(target = "code", source = ".", qualifiedByName = "requirementCode")
  @Mapping(target = "certificationRequirementCode", source = "certificate.id")
  @Mapping(target = "commodityNomenclatureIdCode", source = "commodityCode.id")
  @Mapping(target = "isSelectable", source = ".", qualifiedByName = "selectable")
  @Mapping(target = "tracesIsVisible", constant = "true")
  @Mapping(target = "tracesParentCommodityCode", source = "commodityCode.parent", qualifiedByName = "commodityCode")
  CertificationRequirement map(Commodity commodity);

  @Named("selectable")
  default Boolean selectable(Commodity commodity) {
    return commodity.getId() != null;
  }
}
