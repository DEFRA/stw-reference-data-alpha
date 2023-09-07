package org.defra.payloadbuilder.dao.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.toedter.spring.hateoas.jsonapi.JsonApiTypeForClass;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@JsonApiTypeForClass("commodity_code")
@Data
@Entity
public class CommodityCode {

  @Id
  private String id;

  private String code;

  private String suffix;

  private String description;

  private String sourceName;

  private String sourceId;

  @ManyToOne
  @JsonIgnore
  private CommodityCode parent;
}
