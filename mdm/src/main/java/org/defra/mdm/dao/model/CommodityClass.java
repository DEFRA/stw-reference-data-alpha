package org.defra.mdm.dao.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@Entity(name = "class")
public class CommodityClass {

  @Id
  private int id;

  private String name;

  private String commodityCode;

  private LocalDateTime effectiveFrom;

  private LocalDateTime effectiveTo;
}
