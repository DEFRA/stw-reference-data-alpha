package org.defra.orchestration.apiclient.model;

import com.toedter.spring.hateoas.jsonapi.JsonApiRelationships;
import com.toedter.spring.hateoas.jsonapi.JsonApiTypeForClass;
import lombok.Data;

@JsonApiTypeForClass("commodity")
@Data
public class Commodity {

  private String id;

  @JsonApiRelationships("commodity_code")
  private CommodityCode commodityCode;

  @JsonApiRelationships("species")
  private Species species;
}
