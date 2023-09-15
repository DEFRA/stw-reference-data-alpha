package org.defra.orchestration.mapper;

import org.defra.orchestration.dto.Certificate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CertificateMapper {

  @Mapping(target = "code", source = "id")
  @Mapping(target = "effectiveFrom", expression = "java(java.time.LocalDateTime.of(1970,1,1,0,0))")
  @Mapping(target = "effectiveTo", ignore = true)
  @Mapping(target = "shortDescription", source = "name")
  Certificate map(org.defra.orchestration.apiclient.model.Certificate certificate);
}
