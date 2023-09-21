package org.defra.orchestration.apiclient.model;

import java.time.LocalDateTime;
import lombok.Value;

@Value
public class Variety {

  int id;
  String name;
  String commodityCode;
  String eppo;
  LocalDateTime effectiveFrom;
  LocalDateTime effectiveTo;
}
