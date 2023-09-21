package org.defra.orchestration.apiclient.model;

import com.toedter.spring.hateoas.jsonapi.JsonApiTypeForClass;
import lombok.Value;

@JsonApiTypeForClass("certificate")
@Value
public class Certificate {

  String id;
  String name;
}
