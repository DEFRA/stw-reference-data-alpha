package org.defra.orchestration.apiclient;

import static com.toedter.spring.hateoas.jsonapi.MediaTypes.JSON_API;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.defra.orchestration.apiclient.model.Commodity;
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
        .uri(uriBuilder -> uriBuilder
            .path("/mdm/api")
            .queryParam("filter", "commodity_code")
            .queryParam("effectiveFrom", "2023-01-01T00:00:00Z")
            .queryParam("effectiveTo", "2023-12-01T00:00:00Z")
            .build())
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
}
