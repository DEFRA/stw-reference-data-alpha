package org.defra.payloadbuilder;

import static com.toedter.spring.hateoas.jsonapi.JsonApiModelBuilder.jsonApiModel;

import com.toedter.spring.hateoas.jsonapi.JsonApiModelBuilder;
import java.util.List;
import org.defra.payloadbuilder.dao.CertificateRepository;
import org.defra.payloadbuilder.dao.CommodityCodeRepository;
import org.defra.payloadbuilder.dao.CommodityRepository;
import org.defra.payloadbuilder.dao.SpeciesRepository;
import org.defra.payloadbuilder.dao.model.Certificate;
import org.defra.payloadbuilder.dao.model.Commodity;
import org.defra.payloadbuilder.dao.model.CommodityCode;
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

  @Autowired
  public Controller(
      CommodityRepository commodityRepository,
      CertificateRepository certificateRepository,
      CommodityCodeRepository commodityCodeRepository,
      SpeciesRepository speciesRepository) {
    this.commodityRepository = commodityRepository;
    this.certificateRepository = certificateRepository;
    this.commodityCodeRepository = commodityCodeRepository;
    this.speciesRepository = speciesRepository;
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
    return jsonApiModel()
        .model(commodityModels)
        .included(certificateRepository.findAllByIdIn(certificateIds))
        .included(commodityCodeModels)
        .included(speciesRepository.findAllByIdIn(speciesIds))
        .build();
  }

  private List<CommodityCode> getParents(CommodityCode commodityCode) {
    List<CommodityCode> commodityCodes = new java.util.ArrayList<>();
    commodityCodes.add(commodityCode);
    if (commodityCode.getParent() != null) {
      commodityCodes.addAll(getParents(commodityCode.getParent()));
    }
    return commodityCodes;
  }

  private RepresentationModel<?> getCommodityRepresentation(Commodity commodity) {
    return jsonApiModel()
        .model(commodity)
        .relationship("certificate", commodity.getCertificate())
        .relationship("commodity_code", commodity.getCommodityCode())
        .relationship("species", commodity.getSpecies())
        .build();
  }

  private RepresentationModel<?> getCommodityCodeRepresentation(CommodityCode commodityCode) {
    JsonApiModelBuilder builder = jsonApiModel().model(commodityCode);
    if (commodityCode.getParent() != null) {
      builder.relationship("parent", commodityCode.getParent());
    }
    return builder.build();
  }
}
