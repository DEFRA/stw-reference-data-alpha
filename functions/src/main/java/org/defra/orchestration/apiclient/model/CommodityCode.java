package org.defra.orchestration.apiclient.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.toedter.spring.hateoas.jsonapi.JsonApiRelationships;
import com.toedter.spring.hateoas.jsonapi.JsonApiTypeForClass;
import lombok.Data;

@JsonApiTypeForClass("commodity_code")
@Data
public class CommodityCode {

  private String id;

  private String code;

  private String suffix;

  private String description;

  private String sourceName;

  private String sourceId;

  @JsonApiRelationships("parent")
  private CommodityCode parent;
}
