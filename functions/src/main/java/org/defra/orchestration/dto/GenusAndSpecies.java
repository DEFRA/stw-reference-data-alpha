package org.defra.orchestration.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class GenusAndSpecies {

  String codeId;
  String eppoCode;
  String dataGroup;
  String taxonomicLevel;
  LocalDateTime createdAt;
  LocalDateTime modifiedAt;
  String preferredName;
  String codeLanguage;
  List<CommonName> commonNames;
}
