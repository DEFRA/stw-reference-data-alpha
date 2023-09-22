package org.defra.orchestration.mapper;

import org.defra.orchestration.apiclient.model.CommodityCode;
import org.defra.orchestration.dto.CommodityNomenclature;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(uses = MapperUtils.class)
public interface CommodityNomenclatureMapper {

  @Mapping(target = "code", source = "id")
  @Mapping(target = "commodityNomenclatureParentCode", source = "parent.id")
  @Mapping(target = "sortingKey", source = ".", qualifiedByName = "sortingKey")
  @Mapping(target = "tracesCommodityCode", source = ".", qualifiedByName = "commodityCode")
  @Mapping(target = "tracesCommodityDescription", source = "description")
  CommodityNomenclature map(CommodityCode commodityCode);

  @Named("sortingKey")
  default String sortingKey(CommodityCode commodityCode) {
    return commodityCode.getCode() + commodityCode.getSuffix();
  }
}
