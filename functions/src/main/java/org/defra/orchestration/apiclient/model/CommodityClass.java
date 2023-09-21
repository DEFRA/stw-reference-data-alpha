package org.defra.orchestration.apiclient.model;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class CommodityClass {

  private int id;

  private String name;

  private String commodityCode;

  private LocalDateTime effectiveFrom;

  private LocalDateTime effectiveTo;
}
