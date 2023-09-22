package org.defra.orchestration.mapper;

import org.defra.orchestration.apiclient.model.CommodityGroup;
import org.defra.orchestration.dto.CommodityGroupCommodity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(uses = MapperUtils.class)
public abstract class CommodityGroupCommodityMapper {

  @Autowired
  private MapperUtils mapperUtils;

  @Mapping(target = "code", source = "commodityGroup.id")
  @Mapping(target = "tracesCommodityCode", source = "commodityGroup.commodityCode", qualifiedByName = "trimCommodityCode")
  @Mapping(target = "name", source = "commodityGroup", qualifiedByName = "name")
  @Mapping(target = "commodityGroupCode", source = "commodityGroupId")
  @Mapping(target = "lastChgDateTime", source = "commodityGroup.effectiveFrom")
  public abstract CommodityGroupCommodity map(CommodityGroup commodityGroup, int commodityGroupId);

  @Named("name")
  public String name(CommodityGroup commodityGroup) {
    return String.format("%s %s", mapperUtils.trimCommodityCode(commodityGroup.getCommodityCode()),
        commodityGroup.getName());
  }
}
