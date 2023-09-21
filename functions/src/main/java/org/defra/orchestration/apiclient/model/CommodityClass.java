package org.defra.orchestration.apiclient.model;

import java.time.LocalDateTime;
import lombok.Value;

@Value
public class CommodityClass {

  int id;
  String name;
  String commodityCode;
  LocalDateTime effectiveFrom;
  LocalDateTime effectiveTo;
}
