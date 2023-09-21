package org.defra.orchestration.dto;

import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;

@Value
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class Species extends DataEntity {

  boolean invasiveSpeciesIndicator;
  String speciesCode;
}
