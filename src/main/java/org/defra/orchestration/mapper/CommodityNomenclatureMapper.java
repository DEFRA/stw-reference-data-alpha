package org.defra.orchestration.mapper;

import org.defra.orchestration.apiclient.model.CommodityCode;
import org.defra.orchestration.dto.CommodityNomenclature;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper
public interface CommodityNomenclatureMapper {

  @Mapping(target = "code", source = "id")
  @Mapping(target = "effectiveFrom", expression = "java(java.time.LocalDateTime.of(2023, 1, 1, 0, 0))")
  @Mapping(target = "effectiveTo", ignore = true)
  @Mapping(target = "commodityNomenclatureParentCode", source = "parent.id")
  @Mapping(target = "sortingKey", source = ".", qualifiedByName = "getSortingKey")
  @Mapping(target = "tracesCommodityCode", source = "code", qualifiedByName = "trimCommodityCode")
  @Mapping(target = "tracesCommodityCodeDescription", source = "description")
  CommodityNomenclature map(CommodityCode commodityCode);

  @Named("getSortingKey")
  default String getSortingKey(CommodityCode commodityCode) {
    return commodityCode.getCode() + commodityCode.getSuffix();
  }

  @Named("trimCommodityCode")
  default String trimCommodityCode(String code) {
    return code.replaceAll("(00)*$", "");
  }
}
