package org.defra.orchestration.mapper;

import org.defra.orchestration.apiclient.model.Commodity;
import org.defra.orchestration.dto.Certificate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CertificateMapper {

  @Mapping(target = "code", source = "certificate.id")
  @Mapping(target = "effectiveFrom", source = "commodity.effectiveFrom")
  @Mapping(target = "effectiveTo", source = "commodity.effectiveTo")
  @Mapping(target = "shortDescription", source = "certificate.name")
  Certificate map(org.defra.orchestration.apiclient.model.Certificate certificate, Commodity commodity);
}
