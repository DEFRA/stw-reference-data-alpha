package org.defra.orchestration.mapper;

import org.defra.orchestration.apiclient.model.Commodity;
import org.defra.orchestration.apiclient.model.CommodityCode;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
public class MapperUtils {

  @Named("requirementCode")
  public String requirementCode(Commodity commodity) {
    return commodity.getCertificate().getId() + "-" + commodity.getCommodityCode().getId();
  }

  @Named("commodityCode")
  public String commodityCode(CommodityCode commodityCode) {
    if (commodityCode == null) {
      return null;
    }
    String trimmed = trimCommodityCode(commodityCode.getCode());
    return commodityCode.getSuffix().equals("80")
        ? trimmed
        : trimmed.substring(0, trimmed.length() - 1);
  }

  @Named("trimCommodityCode")
  public String trimCommodityCode(String commodityCode) {
    if (commodityCode == null) {
      return null;
    }
    return commodityCode.replaceAll("(00)*$", "").trim();
  }
}
