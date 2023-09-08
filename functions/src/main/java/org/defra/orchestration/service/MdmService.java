package org.defra.orchestration.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import org.defra.orchestration.apiclient.MdmApiClient;
import org.defra.orchestration.apiclient.model.Commodity;
import org.defra.orchestration.apiclient.model.CommodityCode;
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
        .flatMap(commodityCode -> getParents(commodityCode).stream())
        .distinct()
        .map(commodityNomenclatureMapper::map)
        .toList();
    return buildResponse(data);
  }

  private List<CommodityCode> getParents(CommodityCode commodityCode) {
    if (commodityCode == null) {
      return List.of();
    }
    List<CommodityCode> commodityCodes = new java.util.ArrayList<>();
    commodityCodes.add(commodityCode);
    if (commodityCode.getParent() != null) {
      commodityCodes.addAll(getParents(commodityCode.getParent()));
    }
    return commodityCodes;
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

  private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
    Set<Object> seen = ConcurrentHashMap.newKeySet();
    return t -> seen.add(keyExtractor.apply(t));
  }

  public RdsResponse<CertificationRequirement> getCertificationRequirement() {
    List<Commodity> commodities = apiClient.getCommodities();
    List<Commodity> parentCommodities = new ArrayList<>();
    commodities.forEach(commodity -> {
      List<CommodityCode> parents = getParents(commodity.getCommodityCode());
      parentCommodities.addAll(parents.stream()
          .map(commodityCode -> Commodity.builder()
              .id(commodity.getSpecies().getId() + commodityCode.getId())
              .certificate(commodity.getCertificate())
              .commodityCode(commodityCode)
              .build())
          .toList());
    });
    List<Commodity> outputs = new ArrayList<>();
    outputs.addAll(commodities);
    outputs.addAll(parentCommodities.stream().filter(distinctByKey(Commodity::getId)).toList());
    List<CertificationRequirement> data = outputs.stream()
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
