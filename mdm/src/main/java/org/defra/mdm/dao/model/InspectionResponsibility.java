package org.defra.mdm.dao.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@Entity
public class InspectionResponsibility {

  @Id
  private int id;

  private String commodityCode;

  private String eppo;

  private String inspectionResponsibility;

  private LocalDateTime effectiveFrom;

  private LocalDateTime effectiveTo;
}
