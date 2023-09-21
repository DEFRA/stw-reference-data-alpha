package org.defra.orchestration.apiclient.model;

import java.time.LocalDateTime;
import lombok.Value;

@Value
public class CommodityGroup {

  int id;
  String commodityCode;
  String name;
  LocalDateTime effectiveFrom;
  LocalDateTime effectiveTo;
}
