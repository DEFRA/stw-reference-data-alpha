package org.defra.orchestration.apiclient.model;

import java.time.LocalDateTime;
import lombok.Value;

@Value
public class HmiMarketing {

  int id;
  String commodityCode;
  String eppo;
  String variety;
  int certificateValidityPeriod;
  String hmiMarketingStandard;
  LocalDateTime effectiveFrom;
  LocalDateTime effectiveTo;
}
