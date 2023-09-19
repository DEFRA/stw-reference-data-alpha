package org.defra.orchestration.dto;

import java.util.List;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class PhesResponse {

  List<GenusAndSpecies> data;
  int records;
  int pageNumber;
  int pageSize;
  int totalRecords;
  int totalPages;
}
