package org.defra.orchestration.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

@Value
@SuperBuilder
public class CertificationNomenclature extends DataEntity {

  @JsonProperty("CertificationRequirement_Code")
  String certificationRequirementCode;

  @JsonProperty("CommodityType_Code")
  String commodityTypeCode;

  @JsonProperty("CommodityType_Name")
  String commodityTypeName;

  @JsonProperty("CommodityType_ID")
  Long commodityTypeId;

  @JsonProperty("IsDefaultCommodityType")
  Boolean isDefaultCommodityType;

  @JsonProperty("Class_Code")
  String classCode;

  @JsonProperty("Class_Name")
  String className;

  @JsonProperty("Class_ID")
  Long classId;

  @JsonProperty("IsDefaultClass")
  Boolean isDefaultClass;

  @JsonProperty("Family_Code")
  String familyCode;

  @JsonProperty("Family_Name")
  String familyName;

  @JsonProperty("Family_ID")
  Long familyId;

  @JsonProperty("IsDefaultFamily")
  Boolean isDefaultFamily;

  @JsonProperty("Species_Code")
  String speciesCode;

  @JsonProperty("Species_Name")
  String speciesName;

  @JsonProperty("Species_ID")
  Long speciesId;

  @JsonProperty("IsDefaultSpecies")
  Boolean isDefaultSpecies;
}
