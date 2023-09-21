package org.defra.mdm;

import static com.toedter.spring.hateoas.jsonapi.JsonApiModelBuilder.jsonApiModel;

import com.toedter.spring.hateoas.jsonapi.JsonApiModelBuilder;
import java.util.List;
import java.util.Objects;
import org.defra.mdm.dao.CertificateRepository;
import org.defra.mdm.dao.CommodityClassRepository;
import org.defra.mdm.dao.CommodityCodeRepository;
import org.defra.mdm.dao.CommodityGroupRepository;
import org.defra.mdm.dao.CommodityRepository;
import org.defra.mdm.dao.CommodityTypeRepository;
import org.defra.mdm.dao.HmiMarketingRepository;
import org.defra.mdm.dao.InspectionResponsibilityRepository;
import org.defra.mdm.dao.SpeciesRepository;
import org.defra.mdm.dao.VarietyRepository;
import org.defra.mdm.dao.model.Certificate;
import org.defra.mdm.dao.model.Commodity;
import org.defra.mdm.dao.model.CommodityClass;
import org.defra.mdm.dao.model.CommodityCode;
import org.defra.mdm.dao.model.CommodityGroup;
import org.defra.mdm.dao.model.CommodityType;
import org.defra.mdm.dao.model.HmiMarketing;
import org.defra.mdm.dao.model.InspectionResponsibility;
import org.defra.mdm.dao.model.Species;
import org.defra.mdm.dao.model.Variety;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class Controller {

  private final CommodityRepository commodityRepository;
  private final CertificateRepository certificateRepository;
  private final CommodityCodeRepository commodityCodeRepository;
  private final SpeciesRepository speciesRepository;
  private final CommodityTypeRepository commodityTypeRepository;
  private final CommodityClassRepository commodityClassRepository;
  private final VarietyRepository varietyRepository;
  private final CommodityGroupRepository commodityGroupRepository;
  private final HmiMarketingRepository hmiMarketingRepository;
  private final InspectionResponsibilityRepository inspectionResponsibilityRepository;

  @Autowired
  public Controller(
      CommodityRepository commodityRepository,
      CertificateRepository certificateRepository,
      CommodityCodeRepository commodityCodeRepository,
      SpeciesRepository speciesRepository,
      CommodityTypeRepository commodityTypeRepository,
      CommodityClassRepository commodityClassRepository,
      VarietyRepository varietyRepository,
      CommodityGroupRepository commodityGroupRepository,
      HmiMarketingRepository hmiMarketingRepository,
      InspectionResponsibilityRepository inspectionResponsibilityRepository) {
    this.commodityRepository = commodityRepository;
    this.certificateRepository = certificateRepository;
    this.commodityCodeRepository = commodityCodeRepository;
    this.speciesRepository = speciesRepository;
    this.commodityTypeRepository = commodityTypeRepository;
    this.commodityClassRepository = commodityClassRepository;
    this.varietyRepository = varietyRepository;
    this.commodityGroupRepository = commodityGroupRepository;
    this.hmiMarketingRepository = hmiMarketingRepository;
    this.inspectionResponsibilityRepository = inspectionResponsibilityRepository;
  }

  @GetMapping("/commodities")
  public ResponseEntity<RepresentationModel<?>> getCommodities() {
    List<Commodity> commodities = commodityRepository.findAll();
    CollectionModel<? extends RepresentationModel<?>> commodityModels = CollectionModel.of(
        commodities.stream()
            .map(this::getCommodityRepresentation)
            .toList());
    List<Integer> certificateIds = commodities.stream()
        .map(Commodity::getCertificate)
        .map(Certificate::getId)
        .distinct()
        .toList();
    List<Integer> commodityCodeIds = commodities.stream()
        .map(Commodity::getCommodityCode)
        .flatMap(commodityCode -> getParents(commodityCode).stream())
        .map(CommodityCode::getId)
        .distinct()
        .toList();
    List<?> commodityCodeModels = commodityCodeRepository.findAllByIdIn(commodityCodeIds).stream()
        .map(this::getCommodityCodeRepresentation)
        .toList();
    List<Integer> speciesIds = commodities.stream()
        .map(Commodity::getSpecies)
        .filter(Objects::nonNull)
        .map(Species::getId)
        .distinct()
        .toList();
    List<Integer> commodityTypeIds = commodities.stream()
        .map(Commodity::getCommodityType)
        .filter(Objects::nonNull)
        .map(CommodityType::getId)
        .distinct()
        .toList();
    return ResponseEntity.ok()
        .body(jsonApiModel()
            .model(commodityModels)
            .included(certificateRepository.findAllByIdIn(certificateIds))
            .included(commodityCodeModels)
            .included(speciesRepository.findAllByIdIn(speciesIds))
            .included(commodityTypeRepository.findAllByIdIn(commodityTypeIds))
            .build());
  }

  @GetMapping("/classes")
  public ResponseEntity<Iterable<CommodityClass>> getClasses() {
    return ResponseEntity.ok()
        .body(commodityClassRepository.findAll());
  }

  @GetMapping("/varieties")
  public ResponseEntity<Iterable<Variety>> getVarieties() {
    return ResponseEntity.ok()
        .body(varietyRepository.findAll());
  }

  @GetMapping("/groups")
  public ResponseEntity<Iterable<CommodityGroup>> getGroups() {
    return ResponseEntity.ok()
        .body(commodityGroupRepository.findAll());
  }

  @GetMapping("/hmi-marketing")
  public ResponseEntity<Iterable<HmiMarketing>> getHmiMarketing() {
    return ResponseEntity.ok()
        .body(hmiMarketingRepository.findAll());
  }

  @GetMapping("/inspection-responsibilities")
  public ResponseEntity<Iterable<InspectionResponsibility>> getInspectionResponsibilities() {
    return ResponseEntity.ok()
        .body(inspectionResponsibilityRepository.findAll());
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

  private RepresentationModel<?> getCommodityRepresentation(Commodity commodity) {
    JsonApiModelBuilder builder = jsonApiModel().model(commodity);
    if (commodity.getCertificate() != null) {
      builder.relationship("certificate", commodity.getCertificate());
    }
    if (commodity.getCommodityCode() != null) {
      builder.relationship("commodity_code", commodity.getCommodityCode());
    }
    if (commodity.getSpecies() != null) {
      builder.relationship("species", commodity.getSpecies());
    }
    if (commodity.getCommodityType() != null) {
      builder.relationship("commodity_type", commodity.getCommodityType());
    }
    return builder.build();
  }

  private RepresentationModel<?> getCommodityCodeRepresentation(CommodityCode commodityCode) {
    JsonApiModelBuilder builder = jsonApiModel().model(commodityCode);
    if (commodityCode.getParent() != null) {
      builder.relationship("parent", commodityCode.getParent());
    }
    return builder.build();
  }
}
