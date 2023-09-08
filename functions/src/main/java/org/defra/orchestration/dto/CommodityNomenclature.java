package org.defra.orchestration.dto;

import lombok.Value;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

@Value
@SuperBuilder
public class CommodityNomenclature extends DataEntity {

  String sortingKey;
  String commodityNomenclatureParentCode;
  String tracesCommodityCode;
  String tracesCommodityDescription;
}
