package org.defra.orchestration;

import java.util.function.Function;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Services {

  @Bean
  public Function<String, String> greeter() {
    return name -> {
      log.info("Hello world!");
      return "Hello " + name;
    };
  }
}
