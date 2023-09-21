package org.defra.mdm.dao;

import org.defra.mdm.dao.model.Variety;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VarietyRepository extends CrudRepository<Variety, String> {

}
