package org.defra.mdm.dao;

import org.defra.mdm.dao.model.CommodityClass;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommodityClassRepository extends CrudRepository<CommodityClass, String> {

}
