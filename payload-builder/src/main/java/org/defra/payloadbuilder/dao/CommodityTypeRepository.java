package org.defra.payloadbuilder.dao;

import java.util.Collection;
import java.util.List;
import org.defra.payloadbuilder.dao.model.CommodityType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommodityTypeRepository extends CrudRepository<CommodityType, String> {

  List<CommodityType> findAllByIdIn(Collection<String> ids);
}
