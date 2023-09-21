package org.defra.orchestration.apiclient.model;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class Variety {

  private int id;

  private String name;

  private String commodityCode;

  private String eppo;

  private LocalDateTime effectiveFrom;

  private LocalDateTime effectiveTo;
}
