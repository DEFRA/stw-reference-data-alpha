package org.defra.orchestration.apiclient.model;

import java.time.LocalDateTime;
import lombok.Value;

@Value
public class InspectionResponsibility {

  int id;
  String commodityCode;
  String eppo;
  String inspectionResponsibility;
  LocalDateTime effectiveFrom;
  LocalDateTime effectiveTo;
}
