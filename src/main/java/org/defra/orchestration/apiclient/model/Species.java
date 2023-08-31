package org.defra.orchestration.apiclient.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.toedter.spring.hateoas.jsonapi.JsonApiTypeForClass;
import lombok.Data;

@JsonApiTypeForClass("species")
@Data
public class Species {

  private String id;

  private String eppo;

  @JsonProperty("simple_name")
  private String simpleName;

  private String rank;

  @JsonProperty("class")
  private String clazz;

  private String family;

  private String genus;

  private String species;

  @JsonProperty("source_name")
  private String sourceName;

  @JsonProperty("source_id")
  private String sourceId;
}
