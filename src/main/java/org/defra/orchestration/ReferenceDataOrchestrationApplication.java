package org.defra.orchestration;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableHypermediaSupport(type = {})
public class ReferenceDataOrchestrationApplication {

  @Value("${mdm.baseUrl}")
  private String mdmBaseUrl;

  @Value("${ipaffs.baseUrl}")
  private String ipaffsBaseUrl;

  @Bean
  public ObjectMapper objectMapper() {
    return new ObjectMapper()
        .setSerializationInclusion(Include.NON_NULL)
        .findAndRegisterModules();
  }

  @Bean
  public WebClient mdmWebClient() {
    return WebClient.builder()
        .baseUrl(mdmBaseUrl)
        .build();
  }

  @Bean
  public WebClient ipaffsWebClient() {
    return WebClient.builder()
            .baseUrl(ipaffsBaseUrl)
            .build();
  }

  public static void main(String[] args) {
    SpringApplication.run(ReferenceDataOrchestrationApplication.class, args);
  }
}
