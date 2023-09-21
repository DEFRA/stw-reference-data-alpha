package org.defra.orchestration.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;

@Value
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class CertificationRequirement extends DataEntity {

  @JsonProperty("CertificationRequirement_Code")
  String certificationRequirementCode;

  @JsonProperty("CommodityNomenclatureId_Code")
  String commodityNomenclatureIdCode;

  Boolean isSelectable;

  Boolean tracesIsVisible;

  String tracesParentCommodityCode;
}
