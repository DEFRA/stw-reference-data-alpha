package org.defra.orchestration.mapper;

import org.defra.orchestration.apiclient.model.Commodity;
import org.defra.orchestration.dto.CertificationNomenclature;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper
public interface CertificationNomenclatureMapper {

  @Mapping(target = "code", source = "id")
  @Mapping(target = "certificationRequirementCode", source = ".", qualifiedByName = "requirementCode")
  @Mapping(target = "classCode", ignore = true)
  @Mapping(target = "classId", ignore = true)
  @Mapping(target = "className", source = "species.clazz")
  @Mapping(target = "commodityTypeCode", source = "commodityType.id")
  @Mapping(target = "commodityTypeId", source = "commodityType.id")
  @Mapping(target = "commodityTypeName", source = "commodityType.name")
  @Mapping(target = "familyCode", ignore = true)
  @Mapping(target = "familyId", ignore = true)
  @Mapping(target = "familyName", source = "species.family")
  @Mapping(target = "isDefaultClass", ignore = true)
  @Mapping(target = "isDefaultCommodityType", ignore = true)
  @Mapping(target = "isDefaultFamily", ignore = true)
  @Mapping(target = "isDefaultSpecies", ignore = true)
  @Mapping(target = "speciesCode", source = "species.id")
  @Mapping(target = "speciesId", source = "species.id")
  @Mapping(target = "speciesName", source = "species.species")
  CertificationNomenclature map(Commodity commodity);

  @Named("requirementCode")
  default String requirementCode(Commodity commodity) {
    return commodity.getCertificate().getId() + "-" + commodity.getCommodityCode().getId();
  }
}
