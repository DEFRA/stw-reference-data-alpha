package org.defra.payloadbuilder.dao;

import java.util.Collection;
import java.util.List;
import org.defra.payloadbuilder.dao.model.CommodityCode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommodityCodeRepository extends CrudRepository<CommodityCode, String> {

  List<CommodityCode> findAllByIdIn(Collection<String> ids);
}
