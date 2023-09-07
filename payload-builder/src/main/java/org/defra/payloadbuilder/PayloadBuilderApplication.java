package org.defra.payloadbuilder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.hateoas.config.EnableHypermediaSupport;

@SpringBootApplication
@EnableHypermediaSupport(type = {})
public class PayloadBuilderApplication {

  public static void main(String[] args) {
    SpringApplication.run(PayloadBuilderApplication.class, args);
  }
}
