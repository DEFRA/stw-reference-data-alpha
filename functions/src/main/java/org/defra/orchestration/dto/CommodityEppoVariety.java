package org.defra.orchestration.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;

@Value
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class CommodityEppoVariety extends DataEntity {

  String tracesCommodityCode;

  @JsonProperty("EPPOCode")
  String eppoCode;

  String variety;
}
