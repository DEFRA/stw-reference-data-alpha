package org.defra.orchestration.apiclient.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.toedter.spring.hateoas.jsonapi.JsonApiRelationships;
import com.toedter.spring.hateoas.jsonapi.JsonApiTypeForClass;
import java.time.LocalDateTime;
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

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  private LocalDateTime effectiveFrom;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  private LocalDateTime effectiveTo;
}
