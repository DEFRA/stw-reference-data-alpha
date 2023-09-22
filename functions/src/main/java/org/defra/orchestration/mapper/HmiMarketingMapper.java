package org.defra.orchestration.mapper;

import org.defra.orchestration.apiclient.model.HmiMarketing;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = MapperUtils.class)
public interface HmiMarketingMapper {

  @Mapping(target = "code", source = "id")
  @Mapping(target = "tracesCommodityCode", source = "commodityCode", qualifiedByName = "trimCommodityCode")
  @Mapping(target = "eppoCode", source = "eppo")
  org.defra.orchestration.dto.HmiMarketing map(HmiMarketing hmiMarketing);
}
