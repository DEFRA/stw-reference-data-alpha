package org.defra.mdm.dao;

import org.defra.mdm.dao.model.InspectionResponsibility;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InspectionResponsibilityRepository extends CrudRepository<InspectionResponsibility, String> {

}
