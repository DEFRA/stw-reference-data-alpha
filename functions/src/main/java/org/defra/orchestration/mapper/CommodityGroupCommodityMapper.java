package org.defra.orchestration.mapper;

import org.defra.orchestration.apiclient.model.CommodityGroup;
import org.defra.orchestration.dto.CommodityGroupCommodity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper
public interface CommodityGroupCommodityMapper {

  @Mapping(target = "code", source = "commodityGroup.id")
  @Mapping(target = "tracesCommodityCode", source = "commodityGroup.commodityCode")
  @Mapping(target = "name", source = "commodityGroup", qualifiedByName = "name")
  @Mapping(target = "commodityGroupCode", source = "commodityGroupId")
  @Mapping(target = "lastChgDateTime", source = "commodityGroup.effectiveFrom")
  CommodityGroupCommodity map(CommodityGroup commodityGroup, int commodityGroupId);

  @Named("name")
  default String name(CommodityGroup commodityGroup) {
    return String.format("%s %s", commodityGroup.getCommodityCode(), commodityGroup.getName());
  }
}
