package org.defra.orchestration.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.time.LocalDateTime;
import lombok.Value;
import lombok.experimental.NonFinal;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

@Value
@NonFinal
@SuperBuilder
@Jacksonized
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
public class DataEntity {

  String code;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
  LocalDateTime effectiveFrom;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
  LocalDateTime effectiveTo;
}
