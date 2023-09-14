package org.defra.payloadbuilder.dao.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.toedter.spring.hateoas.jsonapi.JsonApiTypeForClass;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@JsonApiTypeForClass("species")
@Data
@Entity
public class Species {

  @Id
  private String id;

  private String eppo;

  public String getEppo() {
    if (eppo == null) {
      return null;
    } else {
      return eppo.trim();
    }
  }

  private String simpleName;

  private String rank;

  @JsonProperty("class")
  @Column(name = "class")
  private String clazz;

  private String family;

  private String genus;

  private String species;

  private String sourceName;

  private String sourceId;
}
