package org.defra.mdm.dao;

import org.defra.mdm.dao.model.HmiMarketing;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HmiMarketingRepository extends CrudRepository<HmiMarketing, String> {

}
