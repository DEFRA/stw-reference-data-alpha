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
public class IpaffsFunctions {

  private final MdmService mdmService;
  private final ObjectMapper objectMapper;

  @Autowired
  public IpaffsFunctions(MdmService mdmService, ObjectMapper objectMapper) {
    this.mdmService = mdmService;
    this.objectMapper = objectMapper;
  }

  @FunctionName("certificates")
  public HttpResponseMessage certificates(
      @HttpTrigger(
          name = "req",
          methods = HttpMethod.GET,
          authLevel = AuthorizationLevel.FUNCTION
      ) HttpRequestMessage<Optional<String>> request,
      final ExecutionContext context) {
    log.info("certificates HTTP trigger starting");
    return request.createResponseBuilder(HttpStatus.OK)
        .body("certificates")
        .build();
  }

  @FunctionName("commodity-nomenclature")
  public HttpResponseMessage commodityNomenclature(
      @HttpTrigger(
          name = "req",
          methods = HttpMethod.GET,
          authLevel = AuthorizationLevel.FUNCTION
      ) HttpRequestMessage<Optional<String>> request,
      final ExecutionContext context) throws JsonProcessingException {
    log.info("commodity-nomenclature HTTP trigger starting");
    return request.createResponseBuilder(HttpStatus.OK)
        .header("Content-Type", "application/json")
        .body(objectMapper.writeValueAsString(mdmService.getCommodityNomenclature()))
        .build();
  }

  @FunctionName("certification-requirement")
  public HttpResponseMessage certificationRequirement(
      @HttpTrigger(
          name = "req",
          methods = HttpMethod.GET,
          authLevel = AuthorizationLevel.FUNCTION
      ) HttpRequestMessage<Optional<String>> request,
      final ExecutionContext context) {
    log.info("certification-requirement HTTP trigger starting");
    return request.createResponseBuilder(HttpStatus.OK).body("certification-requirement").build();
  }

  @FunctionName("species")
  public HttpResponseMessage species(
      @HttpTrigger(
          name = "req",
          methods = HttpMethod.GET,
          authLevel = AuthorizationLevel.FUNCTION
      ) HttpRequestMessage<Optional<String>> request,
      final ExecutionContext context) throws JsonProcessingException {
    log.info("species HTTP trigger starting");
    return request.createResponseBuilder(HttpStatus.OK)
            .header("Content-Type", "application/json")
            .body(objectMapper.writeValueAsString(mdmService.getSpecies()))
            .build();
  }

  @FunctionName("certification-nomenclature")
  public HttpResponseMessage certificationNomenclature(
      @HttpTrigger(
          name = "req",
          methods = HttpMethod.GET,
          authLevel = AuthorizationLevel.FUNCTION
      ) HttpRequestMessage<Optional<String>> request,
      final ExecutionContext context) {
    log.info("certification-nomenclature HTTP trigger starting");
    return request.createResponseBuilder(HttpStatus.OK).body("certification-nomenclature").build();
  }
}
