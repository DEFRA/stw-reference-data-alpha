package org.defra.mdm.dao.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@Entity
public class CommodityGroup {

  @Id
  private int id;

  private String commodityCode;

  private String name;

  private LocalDateTime effectiveFrom;

  private LocalDateTime effectiveTo;
}
