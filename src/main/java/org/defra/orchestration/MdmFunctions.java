package org.defra.orchestration;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Function;

@Component
@Slf4j
public class MdmFunctions {

  private final Function<String, String> greeter;

  @Autowired
  public MdmFunctions(Function<String, String> greeter) {
    this.greeter = greeter;
  }

  @FunctionName("test")
  public HttpResponseMessage certificates(
      @HttpTrigger(
          name = "req",
          methods = HttpMethod.GET,
          authLevel = AuthorizationLevel.FUNCTION
      ) HttpRequestMessage<Optional<String>> request,
      final ExecutionContext context) {
    log.info("certificates HTTP trigger starting");
    return request.createResponseBuilder(HttpStatus.OK).body("test this").build();
  }

}
