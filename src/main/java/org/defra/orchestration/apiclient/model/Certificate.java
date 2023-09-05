package org.defra.orchestration.apiclient.model;

import com.toedter.spring.hateoas.jsonapi.JsonApiTypeForClass;
import lombok.Data;

@JsonApiTypeForClass("certificate")
@Data
public class Certificate {

  private String id;
  private String name;
}
