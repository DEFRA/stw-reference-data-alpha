package org.defra.payloadbuilder.dao.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.toedter.spring.hateoas.jsonapi.JsonApiTypeForClass;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;

@JsonApiTypeForClass("commodity")
@Data
@Entity
public class Commodity {

  @Id
  private String id;

  @ManyToOne
  @JoinColumn(name = "certificate")
  @JsonIgnore
  private Certificate certificate;

  @ManyToOne
  @JoinColumn(name = "commodity_code")
  @JsonIgnore
  private CommodityCode commodityCode;

  @ManyToOne
  @JoinColumn(name = "species")
  @JsonIgnore
  private Species species;

  @ManyToOne
  @JoinColumn(name = "commodity_type")
  @JsonIgnore
  private CommodityType commodityType;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  private LocalDateTime effectiveFrom;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  private LocalDateTime effectiveTo;
}
