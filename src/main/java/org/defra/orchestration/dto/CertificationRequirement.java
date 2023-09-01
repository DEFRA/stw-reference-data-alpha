package org.defra.orchestration.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Value;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

@Value
@SuperBuilder
@Jacksonized
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
public class CertificationRequirement extends DataEntity {

  @JsonProperty("CertificationRequirement_Code")
  String certificationRequirementCode;

  @JsonProperty("CommodityNomenclatureId_Code")
  String commodityNomenclatureIdCode;

  Boolean isSelectable;

  Boolean isTracesVisible;

  String tracesParentCommodityCode;
}
