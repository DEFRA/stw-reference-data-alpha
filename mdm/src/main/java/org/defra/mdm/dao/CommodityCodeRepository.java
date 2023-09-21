package org.defra.mdm.dao;

import java.util.List;
import org.defra.mdm.dao.model.CommodityCode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommodityCodeRepository extends CrudRepository<CommodityCode, String> {

  List<CommodityCode> findAllByIdIn(List<Integer> ids);
}
