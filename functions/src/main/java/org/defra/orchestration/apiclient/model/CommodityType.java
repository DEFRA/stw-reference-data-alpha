package org.defra.orchestration.apiclient.model;

import com.toedter.spring.hateoas.jsonapi.JsonApiTypeForClass;
import lombok.Value;

@JsonApiTypeForClass("commodity_type")
@Value
public class CommodityType {

  String id;
  String name;
}
