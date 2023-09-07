package org.defra.orchestration.service;

import java.util.List;
import org.defra.orchestration.apiclient.MdmApiClient;
import org.defra.orchestration.apiclient.model.Commodity;
import org.defra.orchestration.dto.Certificate;
import org.defra.orchestration.dto.CertificationNomenclature;
import org.defra.orchestration.dto.CertificationRequirement;
import org.defra.orchestration.dto.CommodityNomenclature;
import org.defra.orchestration.dto.DataEntity;
import org.defra.orchestration.dto.Species;
import org.defra.orchestration.dto.Meta;
import org.defra.orchestration.dto.Pages;
import org.defra.orchestration.dto.RdsResponse;
import org.defra.orchestration.mapper.CertificateMapper;
import org.defra.orchestration.mapper.CertificationNomenclatureMapper;
import org.defra.orchestration.mapper.CertificationRequirementMapper;
import org.defra.orchestration.mapper.CommodityNomenclatureMapper;
import org.defra.orchestration.mapper.SpeciesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MdmService {

  private final MdmApiClient apiClient;
  private final CommodityNomenclatureMapper commodityNomenclatureMapper;
  private final SpeciesMapper speciesMapper;
  private final CertificateMapper certificateMapper;
  private final CertificationRequirementMapper certificationRequirementMapper;
  private final CertificationNomenclatureMapper certificationNomenclatureMapper;

  @Autowired
  public MdmService(
      MdmApiClient apiClient,
      CommodityNomenclatureMapper commodityNomenclatureMapper,
      CertificateMapper certificateMapper,
      CertificationRequirementMapper certificationRequirementMapper,
      CertificationNomenclatureMapper certificationNomenclatureMapper,
      SpeciesMapper speciesMapper) {
    this.apiClient = apiClient;
    this.commodityNomenclatureMapper = commodityNomenclatureMapper;
    this.certificateMapper = certificateMapper;
    this.certificationRequirementMapper = certificationRequirementMapper;
    this.certificationNomenclatureMapper = certificationNomenclatureMapper;
    this.speciesMapper = speciesMapper;
  }

  public RdsResponse<CommodityNomenclature> getCommodityNomenclature() {
    List<Commodity> commodities = apiClient.getCommodities();
    List<CommodityNomenclature> data = commodities.stream()
        .map(Commodity::getCommodityCode)
        .distinct()
        .map(commodityNomenclatureMapper::map)
        .toList();
    return buildResponse(data);
  }

  public RdsResponse<Certificate> getCertificates() {
    List<Commodity> commodities = apiClient.getCommodities();
    List<Certificate> data = commodities.stream()
        .map(Commodity::getCertificate)
        .distinct()
        .map(certificateMapper::map)
        .toList();
    return buildResponse(data);
  }

  public RdsResponse<CertificationRequirement> getCertificationRequirement() {
    List<Commodity> commodities = apiClient.getCommodities();
    List<CertificationRequirement> data = commodities.stream()
        .map(certificationRequirementMapper::map)
        .toList();
    return buildResponse(data);
  }

  public RdsResponse<CertificationNomenclature> getCertificationNomenclature() {
    List<Commodity> commodities = apiClient.getCommodities();
    List<CertificationNomenclature> data = commodities.stream()
        .map(certificationNomenclatureMapper::map)
        .toList();
    return buildResponse(data);
  }

  public RdsResponse<Species> getSpecies() {
    List<Commodity> commodities = apiClient.getCommodities();
    List<Species> data = commodities.stream()
            .map(Commodity::getSpecies)
            .distinct()
            .map(speciesMapper::map)
            .toList();
    return buildResponse(data);
  }

  private <T extends DataEntity> RdsResponse<T> buildResponse(List<T> data) {
    return RdsResponse.<T>builder()
        .meta(Meta.builder()
            .count(data.size())
            .total(data.size())
            .pages(Pages.builder().build())
            .build())
        .data(data)
        .build();
  }
}
