package org.defra.orchestration.mapper;

import org.defra.orchestration.apiclient.model.CommodityGroup;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CommodityGroupMapper {

  @Mapping(target = "code", source = "id")
  org.defra.orchestration.dto.CommodityGroup map(CommodityGroup commodityClass);
}
