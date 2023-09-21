package org.defra.mdm.dao.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@Entity
public class Variety {

  @Id
  private int id;

  private String name;

  private String commodityCode;

  private String eppo;

  public String getEppo() {
    if (eppo == null) {
      return null;
    } else {
      return eppo.trim();
    }
  }

  private LocalDateTime effectiveFrom;

  private LocalDateTime effectiveTo;
}
