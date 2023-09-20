package org.defra.mdm.dao;

import java.util.Collection;
import java.util.List;
import org.defra.mdm.dao.model.Certificate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificateRepository extends CrudRepository<Certificate, String> {

  List<Certificate> findAllByIdIn(Collection<String> ids);
}
