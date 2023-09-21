package org.defra.orchestration.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import lombok.experimental.SuperBuilder;

@Value
@SuperBuilder
public class CommodityClass extends DataEntity {

  String tracesCommodityCode;

  @JsonProperty("Class")
  String clazz;
}
