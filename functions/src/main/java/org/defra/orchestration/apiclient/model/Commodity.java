package org.defra.orchestration.apiclient.model;

import com.toedter.spring.hateoas.jsonapi.JsonApiRelationships;
import com.toedter.spring.hateoas.jsonapi.JsonApiTypeForClass;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@JsonApiTypeForClass("commodity")
@Data
@Builder
@Jacksonized
@RequiredArgsConstructor
@AllArgsConstructor
public class Commodity {

  private String id;

  @JsonApiRelationships("certificate")
  private Certificate certificate;

  @JsonApiRelationships("commodity_code")
  private CommodityCode commodityCode;

  @JsonApiRelationships("species")
  private Species species;

  @JsonApiRelationships("commodity_type")
  private CommodityType commodityType;

  private LocalDateTime effectiveFrom;

  private LocalDateTime effectiveTo;
}
