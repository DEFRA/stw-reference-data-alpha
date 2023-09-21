package org.defra.orchestration.apiclient.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.toedter.spring.hateoas.jsonapi.JsonApiTypeForClass;
import lombok.Value;

@JsonApiTypeForClass("species")
@Value
public class Species {

  String id;

  String eppo;

  String simpleName;

  String rank;

  String kingdom;

  @JsonProperty("class")
  String clazz;

  String family;

  String genus;

  String species;

  String sourceName;

  String sourceId;
}
