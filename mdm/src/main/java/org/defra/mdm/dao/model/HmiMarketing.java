package org.defra.mdm.dao.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@Entity
public class HmiMarketing {

  @Id
  private int id;

  private String commodityCode;

  private String eppo;

  private String variety;

  private int certificateValidityPeriod;

  private String hmiMarketingStandard;

  private LocalDateTime effectiveFrom;

  private LocalDateTime effectiveTo;
}
