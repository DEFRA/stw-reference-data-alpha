package org.defra.orchestration.apiclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class IpaffsApiClient {

  private final WebClient webClient;

  @Autowired
  public IpaffsApiClient(WebClient ipaffsWebClient) {
    this.webClient = ipaffsWebClient;
  }

  public HttpStatusCode syncCommodityCodes(String authToken) {
    return webClient.post()
            .uri("/referencedataloader/commodity-codes/sync")
            .header("Authorization", authToken)
            .accept(MediaType.APPLICATION_JSON)
            .exchangeToMono(response -> Mono.just(response.statusCode()))
            .block();
  }
}
