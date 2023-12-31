package org.defra.orchestration.service;

import java.time.LocalDateTime;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.defra.orchestration.apiclient.MdmApiClient;
import org.defra.orchestration.apiclient.model.Commodity;
import org.defra.orchestration.apiclient.model.CommodityCode;
import org.defra.orchestration.apiclient.model.CommodityGroup;
import org.defra.orchestration.dto.Certificate;
import org.defra.orchestration.dto.CertificationNomenclature;
import org.defra.orchestration.dto.CertificationRequirement;
import org.defra.orchestration.dto.CommodityClass;
import org.defra.orchestration.dto.CommodityEppoVariety;
import org.defra.orchestration.dto.CommodityGroupCommodity;
import org.defra.orchestration.dto.CommodityNomenclature;
import org.defra.orchestration.dto.DataEntity;
import org.defra.orchestration.dto.GenusAndSpecies;
import org.defra.orchestration.dto.HmiMarketing;
import org.defra.orchestration.dto.InspectionResponsibility;
import org.defra.orchestration.dto.Meta;
import org.defra.orchestration.dto.Pages;
import org.defra.orchestration.dto.PhesResponse;
import org.defra.orchestration.dto.RdsResponse;
import org.defra.orchestration.dto.Species;
import org.defra.orchestration.mapper.CertificateMapper;
import org.defra.orchestration.mapper.CertificationNomenclatureMapper;
import org.defra.orchestration.mapper.CertificationRequirementMapper;
import org.defra.orchestration.mapper.CommodityClassMapper;
import org.defra.orchestration.mapper.CommodityEppoVarietyMapper;
import org.defra.orchestration.mapper.CommodityGroupCommodityMapper;
import org.defra.orchestration.mapper.CommodityGroupMapper;
import org.defra.orchestration.mapper.CommodityNomenclatureMapper;
import org.defra.orchestration.mapper.GenusAndSpeciesMapper;
import org.defra.orchestration.mapper.HmiMarketingMapper;
import org.defra.orchestration.mapper.InspectionResponsibilityMapper;
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
  private final GenusAndSpeciesMapper genusAndSpeciesMapper;
  private final CommodityClassMapper commodityClassMapper;
  private final CommodityEppoVarietyMapper commodityEppoVarietyMapper;
  private final CommodityGroupMapper commodityGroupMapper;
  private final CommodityGroupCommodityMapper commodityGroupCommodityMapper;
  private final HmiMarketingMapper hmiMarketingMapper;
  private final InspectionResponsibilityMapper inspectionResponsibilityMapper;

  @Autowired
  public MdmService(
      MdmApiClient apiClient,
      CommodityNomenclatureMapper commodityNomenclatureMapper,
      CertificateMapper certificateMapper,
      CertificationRequirementMapper certificationRequirementMapper,
      CertificationNomenclatureMapper certificationNomenclatureMapper,
      SpeciesMapper speciesMapper,
      GenusAndSpeciesMapper genusAndSpeciesMapper,
      CommodityClassMapper commodityClassMapper,
      CommodityEppoVarietyMapper commodityEppoVarietyMapper,
      CommodityGroupMapper commodityGroupMapper,
      CommodityGroupCommodityMapper commodityGroupCommodityMapper,
      HmiMarketingMapper hmiMarketingMapper,
      InspectionResponsibilityMapper inspectionResponsibilityMapper) {
    this.apiClient = apiClient;
    this.commodityNomenclatureMapper = commodityNomenclatureMapper;
    this.certificateMapper = certificateMapper;
    this.certificationRequirementMapper = certificationRequirementMapper;
    this.certificationNomenclatureMapper = certificationNomenclatureMapper;
    this.speciesMapper = speciesMapper;
    this.genusAndSpeciesMapper = genusAndSpeciesMapper;
    this.commodityClassMapper = commodityClassMapper;
    this.commodityEppoVarietyMapper = commodityEppoVarietyMapper;
    this.commodityGroupMapper = commodityGroupMapper;
    this.commodityGroupCommodityMapper = commodityGroupCommodityMapper;
    this.hmiMarketingMapper = hmiMarketingMapper;
    this.inspectionResponsibilityMapper = inspectionResponsibilityMapper;
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

  private Predicate<Commodity> matches(
      CommodityCode commodityCode,
      org.defra.orchestration.apiclient.model.Certificate certificate) {
    return x -> x.getCommodityCode().equals(commodityCode)
        && x.getCertificate().equals(certificate);
  }

  public RdsResponse<CertificationRequirement> getCertificationRequirement() {
    List<Commodity> commodities = apiClient.getCommodities();

    // Find the distinct commodity code and certificate combinations
    List<Commodity> outputs = new ArrayList<>();
    commodities.forEach(commodity -> outputs.stream()
        .filter(matches(commodity.getCommodityCode(), commodity.getCertificate()))
        .findFirst()
        .ifPresentOrElse(commodityCodeAndCertificate -> {
          // Take the earliest effective from
          if (commodity.getEffectiveFrom().isBefore(
              commodityCodeAndCertificate.getEffectiveFrom())) {
            commodityCodeAndCertificate.setEffectiveFrom(commodity.getEffectiveFrom());
          }
          // Take the latest effective to (null is latest)
          if (commodity.getEffectiveTo() == null) {
            commodityCodeAndCertificate.setEffectiveTo(null);
          } else if (commodity.getEffectiveTo()
              .isAfter(commodityCodeAndCertificate.getEffectiveTo())) {
            commodityCodeAndCertificate.setEffectiveTo(commodity.getEffectiveTo());
          }
        }, () -> outputs.add(Commodity.builder()
            .id(commodity.getId())
            .certificate(commodity.getCertificate())
            .commodityCode(commodity.getCommodityCode())
            .effectiveFrom(commodity.getEffectiveFrom())
            .effectiveTo(commodity.getEffectiveTo())
            .build())));

    // Loop through and add the parent commodity code and certificate combinations
    for (int i = 0; i < outputs.size(); i++) {
      CommodityCode parent = outputs.get(i).getCommodityCode().getParent();
      if (parent != null &&
          outputs.stream().noneMatch(matches(parent, outputs.get(i).getCertificate()))) {
        outputs.add(Commodity.builder()
            .certificate(outputs.get(i).getCertificate())
            .commodityCode(parent)
            .effectiveFrom(parent.getEffectiveFrom())
            .effectiveTo(parent.getEffectiveTo())
            .build());
      }
    }

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
        .filter(Objects::nonNull)
        .distinct()
        .map(species -> {
          LocalDateTime effectiveFrom = LocalDateTime.MAX;
          LocalDateTime effectiveTo = LocalDateTime.MIN;
          for (Commodity commodity : commodities.stream()
              .filter(commodity -> Objects.equals(commodity.getSpecies(), species))
              .toList()) {
            // Get the earliest effective from
            if (commodity.getEffectiveFrom().isBefore(effectiveFrom)) {
              effectiveFrom = commodity.getEffectiveFrom();
            }
            // Get latest effective to (null is latest)
            if (commodity.getEffectiveTo() == null) {
              effectiveTo = null;
            } else if (commodity.getEffectiveTo().isAfter(effectiveTo)) {
              effectiveTo = commodity.getEffectiveTo();
            }
          }
          return speciesMapper.map(species, effectiveFrom, effectiveTo);
        })
        .toList();
    return buildResponse(data);
  }

  public RdsResponse<CommodityClass> getClasses() {
    List<CommodityClass> data = apiClient.getClasses().stream()
        .map(commodityClassMapper::map)
        .toList();
    return buildResponse(data);
  }

  public RdsResponse<CommodityEppoVariety> getVarieties() {
    List<CommodityEppoVariety> data = apiClient.getVarieties().stream()
        .map(commodityEppoVarietyMapper::map)
        .toList();
    return buildResponse(data);
  }

  public RdsResponse<org.defra.orchestration.dto.CommodityGroup> getCommodityGroups() {
    List<org.defra.orchestration.dto.CommodityGroup> data = apiClient.getGroups().stream()
        .filter(distinctByKey(CommodityGroup::getName))
        .map(commodityGroupMapper::map)
        .toList();
    return buildResponse(data);
  }

  public RdsResponse<CommodityGroupCommodity> getCommodityGroupCommodity() {
    List<CommodityGroup> groups = apiClient.getGroups();
    Map<Integer, List<CommodityGroup>> groupMap = groups.stream().collect(Collectors.toMap(
        group -> groups.stream()
            .filter(g -> g.getName().equals(group.getName()))
            .findFirst()
            .map(CommodityGroup::getId)
            .orElseThrow(),
        List::of,
        (a, b) -> Stream.of(a, b).flatMap(List::stream).toList()
    ));
    List<CommodityGroupCommodity> data = groupMap.entrySet().stream()
        .flatMap(entry -> entry.getValue().stream()
            .map(group -> new SimpleEntry<>(entry.getKey(), group)))
        .map(entry -> commodityGroupCommodityMapper.map(entry.getValue(), entry.getKey()))
        .toList();
    return buildResponse(data);
  }

  public RdsResponse<HmiMarketing> getHmiMarketing() {
    List<HmiMarketing> data = apiClient.getHmiMarketing().stream()
        .map(hmiMarketingMapper::map)
        .toList();
    return buildResponse(data);
  }

  public RdsResponse<InspectionResponsibility> getInspectionResponsibility() {
    List<InspectionResponsibility> data = apiClient.getInspectionResponsibilities().stream()
        .map(inspectionResponsibilityMapper::map)
        .toList();
    return buildResponse(data);
  }

  private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
    Set<Object> seen = ConcurrentHashMap.newKeySet();
    return t -> seen.add(keyExtractor.apply(t));
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

  public PhesResponse getGenusAndSpecies() {
    List<GenusAndSpecies> data = apiClient.getCommodities().stream()
        .map(Commodity::getSpecies)
        .distinct()
        .filter(species -> species.getKingdom().equals("Plantae"))
        .map(genusAndSpeciesMapper::map)
        .toList();
    return PhesResponse.builder()
        .data(data)
        .records(data.size())
        .pageNumber(1)
        .pageSize(data.size())
        .totalRecords(data.size())
        .totalPages(1)
        .build();
  }
}
