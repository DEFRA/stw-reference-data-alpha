package org.defra.payloadbuilder.dao.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.toedter.spring.hateoas.jsonapi.JsonApiTypeForClass;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

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
}
