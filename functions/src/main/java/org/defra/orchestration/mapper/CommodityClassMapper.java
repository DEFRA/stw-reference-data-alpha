package org.defra.orchestration.mapper;

import org.defra.orchestration.apiclient.model.CommodityClass;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = MapperUtils.class)
public interface CommodityClassMapper {

  @Mapping(target = "code", source = "id")
  @Mapping(target = "tracesCommodityCode", source = "commodityCode", qualifiedByName = "trimCommodityCode")
  @Mapping(target = "clazz", source = "name")
  org.defra.orchestration.dto.CommodityClass map(CommodityClass commodityClass);
}
