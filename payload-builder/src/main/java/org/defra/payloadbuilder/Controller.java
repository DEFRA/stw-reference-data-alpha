package org.defra.payloadbuilder;

import static com.toedter.spring.hateoas.jsonapi.JsonApiModelBuilder.jsonApiModel;

import com.toedter.spring.hateoas.jsonapi.JsonApiModelBuilder;
import java.util.List;
import java.util.Objects;
import org.defra.payloadbuilder.dao.CertificateRepository;
import org.defra.payloadbuilder.dao.CommodityCodeRepository;
import org.defra.payloadbuilder.dao.CommodityRepository;
import org.defra.payloadbuilder.dao.CommodityTypeRepository;
import org.defra.payloadbuilder.dao.SpeciesRepository;
import org.defra.payloadbuilder.dao.model.Certificate;
import org.defra.payloadbuilder.dao.model.Commodity;
import org.defra.payloadbuilder.dao.model.CommodityCode;
import org.defra.payloadbuilder.dao.model.CommodityType;
import org.defra.payloadbuilder.dao.model.Species;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

  private final CommodityRepository commodityRepository;
  private final CertificateRepository certificateRepository;
  private final CommodityCodeRepository commodityCodeRepository;
  private final SpeciesRepository speciesRepository;
  private final CommodityTypeRepository commodityTypeRepository;

  @Autowired
  public Controller(
      CommodityRepository commodityRepository,
      CertificateRepository certificateRepository,
      CommodityCodeRepository commodityCodeRepository,
      SpeciesRepository speciesRepository,
      CommodityTypeRepository commodityTypeRepository) {
    this.commodityRepository = commodityRepository;
    this.certificateRepository = certificateRepository;
    this.commodityCodeRepository = commodityCodeRepository;
    this.speciesRepository = speciesRepository;
    this.commodityTypeRepository = commodityTypeRepository;
  }

  @GetMapping("/payload")
  public RepresentationModel<?> getPayload() {
    List<Commodity> commodities = commodityRepository.findAll();
    CollectionModel<? extends RepresentationModel<?>> commodityModels = CollectionModel.of(
        commodities.stream()
            .map(this::getCommodityRepresentation)
            .toList());
    List<String> certificateIds = commodities.stream()
        .map(Commodity::getCertificate)
        .map(Certificate::getId)
        .distinct()
        .toList();
    List<String> commodityCodeIds = commodities.stream()
        .map(Commodity::getCommodityCode)
        .flatMap(commodityCode -> getParents(commodityCode).stream())
        .map(CommodityCode::getId)
        .distinct()
        .toList();
    List<?> commodityCodeModels = commodityCodeRepository.findAllByIdIn(commodityCodeIds).stream()
        .map(this::getCommodityCodeRepresentation)
        .toList();
    List<String> speciesIds = commodities.stream()
        .map(Commodity::getSpecies)
        .map(Species::getId)
        .distinct()
        .toList();
    List<String> commodityTypeIds = commodities.stream()
        .map(Commodity::getCommodityType)
        .filter(Objects::nonNull)
        .map(CommodityType::getId)
        .distinct()
        .toList();
    return jsonApiModel()
        .model(commodityModels)
        .included(certificateRepository.findAllByIdIn(certificateIds))
        .included(commodityCodeModels)
        .included(speciesRepository.findAllByIdIn(speciesIds))
        .included(commodityTypeRepository.findAllByIdIn(commodityTypeIds))
        .build();
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
