package org.defra.orchestration.apiclient;

import static com.toedter.spring.hateoas.jsonapi.MediaTypes.JSON_API;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.defra.orchestration.apiclient.model.Commodity;
import org.defra.orchestration.apiclient.model.CommodityClass;
import org.defra.orchestration.apiclient.model.Variety;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@Slf4j
public class MdmApiClient {

  private final WebClient webClient;

  @Autowired
  public MdmApiClient(WebClient mdmWebClient) {
    this.webClient = mdmWebClient;
  }

  public List<Commodity> getCommodities() {
    CollectionModel<EntityModel<Commodity>> commodities = webClient.get()
        .uri("/commodities")
        .accept(JSON_API)
        .retrieve()
        .bodyToMono(new ParameterizedTypeReference<CollectionModel<EntityModel<Commodity>>>() {
        })
        .block();
    if (commodities != null) {
      return commodities.getContent().stream()
          .map(EntityModel::getContent)
          .toList();
    } else {
      return List.of();
    }
  }

  public List<CommodityClass> getClasses() {
    return webClient.get()
        .uri("/classes")
        .accept(APPLICATION_JSON)
        .retrieve()
        .bodyToFlux(CommodityClass.class)
        .collectList()
        .block();
  }

  public List<Variety> getVarieties() {
    return webClient.get()
        .uri("/varieties")
        .accept(APPLICATION_JSON)
        .retrieve()
        .bodyToFlux(Variety.class)
        .collectList()
        .block();
  }
}
