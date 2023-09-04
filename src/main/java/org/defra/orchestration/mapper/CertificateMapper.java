package org.defra.orchestration.mapper;

import org.defra.orchestration.dto.Certificate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CertificateMapper {

  @Mapping(target = "code", source = "id")
  @Mapping(target = "shortDescription", source = "name")
  @Mapping(target = "effectiveFrom", ignore = true)
  @Mapping(target = "effectiveTo", ignore = true)
  Certificate map(org.defra.orchestration.apiclient.model.Certificate certificate);
}
