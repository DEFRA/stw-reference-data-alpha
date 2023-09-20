package org.defra.mdm.dao.model;

import com.toedter.spring.hateoas.jsonapi.JsonApiTypeForClass;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@JsonApiTypeForClass("commodity_type")
@Data
@Entity
public class CommodityType {

  @Id
  private String id;

  private String name;
}
