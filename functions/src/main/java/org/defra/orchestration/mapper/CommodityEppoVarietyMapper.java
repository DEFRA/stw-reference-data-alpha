package org.defra.orchestration.mapper;

import org.defra.orchestration.apiclient.model.Variety;
import org.defra.orchestration.dto.CommodityEppoVariety;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = MapperUtils.class)
public interface CommodityEppoVarietyMapper {

  @Mapping(target = "code", source = "id")
  @Mapping(target = "tracesCommodityCode", source = "commodityCode", qualifiedByName = "trimCommodityCode")
  @Mapping(target = "eppoCode", source = "eppo")
  @Mapping(target = "variety", source = "name")
  CommodityEppoVariety map(Variety variety);
}
