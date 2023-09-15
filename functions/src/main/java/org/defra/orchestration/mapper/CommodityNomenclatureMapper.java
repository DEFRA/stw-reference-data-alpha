package org.defra.orchestration.mapper;

import org.defra.orchestration.apiclient.model.Commodity;
import org.defra.orchestration.apiclient.model.CommodityCode;
import org.defra.orchestration.dto.CommodityNomenclature;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper
public interface CommodityNomenclatureMapper {

  @Mapping(target = "code", source = "commodityCode.id")
  @Mapping(target = "effectiveFrom", source = "commodity.effectiveFrom")
  @Mapping(target = "effectiveTo", source = "commodity.effectiveTo")
  @Mapping(target = "commodityNomenclatureParentCode", source = "commodityCode.parent.id")
  @Mapping(target = "sortingKey", source = "commodityCode", qualifiedByName = "sortingKey")
  @Mapping(target = "tracesCommodityCode", source = "commodityCode", qualifiedByName = "commodityCode")
  @Mapping(target = "tracesCommodityDescription", source = "commodityCode.description")
  CommodityNomenclature map(CommodityCode commodityCode, Commodity commodity);

  @Named("sortingKey")
  default String sortingKey(CommodityCode commodityCode) {
    return commodityCode.getCode() + commodityCode.getSuffix();
  }

  @Named("commodityCode")
  default String commodityCode(CommodityCode commodityCode) {
    String trimmed = commodityCode.getCode().replaceAll("(00)*$", "");
    return commodityCode.getSuffix().equals("80")
        ? trimmed
        : trimmed.substring(0, trimmed.length() - 1);
  }
}
