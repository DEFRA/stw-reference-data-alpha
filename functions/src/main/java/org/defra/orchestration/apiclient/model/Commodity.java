package org.defra.orchestration.apiclient.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.toedter.spring.hateoas.jsonapi.JsonApiRelationships;
import com.toedter.spring.hateoas.jsonapi.JsonApiTypeForClass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;

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

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  private LocalDateTime effectiveFrom;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  private LocalDateTime effectiveTo;
}
