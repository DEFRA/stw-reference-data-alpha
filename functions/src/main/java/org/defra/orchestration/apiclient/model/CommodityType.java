package org.defra.orchestration.apiclient.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.toedter.spring.hateoas.jsonapi.JsonApiTypeForClass;
import lombok.Data;

@JsonApiTypeForClass("commodity_type")
@Data
public class CommodityType {

  private String id;

  private String name;
}
