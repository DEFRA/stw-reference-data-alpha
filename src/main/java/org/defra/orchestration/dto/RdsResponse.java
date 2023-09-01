package org.defra.orchestration.dto;

import java.util.List;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class RdsResponse<T extends DataEntity> {

  Meta meta;
  List<T> data;
}
