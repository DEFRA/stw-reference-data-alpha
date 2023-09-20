package org.defra.mdm;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.toedter.spring.hateoas.jsonapi.JsonApiConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.config.EnableHypermediaSupport;

@SpringBootApplication
@EnableHypermediaSupport(type = {})
public class PayloadBuilderApplication {

  @Bean
  JsonApiConfiguration jsonApiConfiguration() {
    return new JsonApiConfiguration()
        .withObjectMapperCustomizer(objectMapper -> objectMapper
            .findAndRegisterModules()
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS));
  }

  public static void main(String[] args) {
    SpringApplication.run(PayloadBuilderApplication.class, args);
  }
}
