package org.defra.orchestration.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Value;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

@Value
@SuperBuilder
public class Species extends DataEntity {

  boolean invasiveSpeciesIndicator;
  String speciesCode;
}
