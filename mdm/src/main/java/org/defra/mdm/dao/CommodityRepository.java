package org.defra.mdm.dao;

import java.util.List;
import org.defra.mdm.dao.model.Commodity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommodityRepository extends CrudRepository<Commodity, String> {

  List<Commodity> findAll();
}
