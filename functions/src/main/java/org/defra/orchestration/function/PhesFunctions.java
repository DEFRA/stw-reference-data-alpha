package org.defra.orchestration.function;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.defra.orchestration.service.MdmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PhesFunctions {

  private final MdmService mdmService;
  private final ObjectMapper objectMapper;

  @Autowired
  public PhesFunctions(MdmService mdmService, ObjectMapper objectMapper) {
    this.mdmService = mdmService;
    this.objectMapper = objectMapper;
  }

  @FunctionName("genus-and-species")
  public HttpResponseMessage genusAndSpecies(
      @HttpTrigger(
          name = "req",
          methods = HttpMethod.GET,
          authLevel = AuthorizationLevel.ANONYMOUS
      ) HttpRequestMessage<Optional<String>> request,
      final ExecutionContext context) throws JsonProcessingException {
    log.info("genus-and-species HTTP trigger starting");
    return request.createResponseBuilder(HttpStatus.OK)
        .header("Content-Type", "application/json")
        .body(objectMapper.writeValueAsString(mdmService.getGenusAndSpecies()))
        .build();
  }
}
