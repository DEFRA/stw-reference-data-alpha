package org.defra.orchestration.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class Pages {

  String next;
  String previous;
}
