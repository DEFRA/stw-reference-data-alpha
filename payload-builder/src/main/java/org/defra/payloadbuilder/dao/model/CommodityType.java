package org.defra.payloadbuilder.dao.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.toedter.spring.hateoas.jsonapi.JsonApiTypeForClass;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@JsonApiTypeForClass("commodity_type")
@Data
@Entity
public class CommodityType {

  @Id
  private String id;

  private String name;
}
