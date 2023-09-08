package org.defra.orchestration.apiclient.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.toedter.spring.hateoas.jsonapi.JsonApiTypeForClass;
import lombok.Data;

@JsonApiTypeForClass("species")
@Data
public class Species {

  private String id;

  private String eppo;

  private String simpleName;

  private String rank;

  @JsonProperty("class")
  private String clazz;

  private String family;

  private String genus;

  private String species;

  private String sourceName;

  private String sourceId;
}
