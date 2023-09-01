package org.defra.orchestration.apiclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class MdmApiClient {

  private final WebClient webClient;

  @Autowired
  public MdmApiClient(WebClient mdmWebClient) {
    this.webClient = mdmWebClient;
  }

  public HttpStatusCode syncCommodityCodes() {
    return webClient.post()
            .uri("/commodity-codes/sync")
            .exchangeToMono(response -> Mono.just(response.statusCode()))
            .block();
  }
}
