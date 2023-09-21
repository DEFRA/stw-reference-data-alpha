package org.defra.mdm.dao;

import org.defra.mdm.dao.model.CommodityGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommodityGroupRepository extends CrudRepository<CommodityGroup, String> {

}
