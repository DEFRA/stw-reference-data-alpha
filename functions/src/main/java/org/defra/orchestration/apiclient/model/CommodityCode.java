package org.defra.orchestration.apiclient.model;

import com.toedter.spring.hateoas.jsonapi.JsonApiRelationships;
import com.toedter.spring.hateoas.jsonapi.JsonApiTypeForClass;
import java.time.LocalDateTime;
import lombok.Value;

@JsonApiTypeForClass("commodity_code")
@Value
public class CommodityCode {

  String id;

  String code;

  String suffix;

  String description;

  String sourceName;

  String sourceId;

  @JsonApiRelationships("parent")
  CommodityCode parent;

  LocalDateTime effectiveFrom;

  LocalDateTime effectiveTo;
}
