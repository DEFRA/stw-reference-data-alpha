package org.defra.payloadbuilder.dao;

import java.util.Collection;
import java.util.List;
import org.defra.payloadbuilder.dao.model.Species;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpeciesRepository extends CrudRepository<Species, String> {

  List<Species> findAllByIdIn(Collection<String> ids);
}
