package org.defra.orchestration.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;

@Value
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class CommodityGroupCommodity extends DataEntity {

  String name;

  String tracesCommodityCode;

  @JsonProperty("CommodityGroup_Code")
  String commodityGroupCode;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
  LocalDateTime lastChgDateTime;
}
