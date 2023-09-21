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
          authLevel = AuthorizationLevel.ANONYMOUS
      ) HttpRequestMessage<Optional<String>> request,
      final ExecutionContext context) throws JsonProcessingException {
    log.info("certificates HTTP trigger starting");
    return request.createResponseBuilder(HttpStatus.OK)
        .header("Content-Type", "application/json")
        .body(objectMapper.writeValueAsString(mdmService.getCertificates()))
        .build();
  }

  @FunctionName("commodity-nomenclature")
  public HttpResponseMessage commodityNomenclature(
      @HttpTrigger(
          name = "req",
          methods = HttpMethod.GET,
          authLevel = AuthorizationLevel.ANONYMOUS
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
          authLevel = AuthorizationLevel.ANONYMOUS
      ) HttpRequestMessage<Optional<String>> request,
      final ExecutionContext context) throws JsonProcessingException {
    log.info("certification-requirement HTTP trigger starting");
    return request.createResponseBuilder(HttpStatus.OK)
        .header("Content-Type", "application/json")
        .body(objectMapper.writeValueAsString(mdmService.getCertificationRequirement()))
        .build();
  }

  @FunctionName("species")
  public HttpResponseMessage species(
      @HttpTrigger(
          name = "req",
          methods = HttpMethod.GET,
          authLevel = AuthorizationLevel.ANONYMOUS
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
          authLevel = AuthorizationLevel.ANONYMOUS
      ) HttpRequestMessage<Optional<String>> request,
      final ExecutionContext context) throws JsonProcessingException {
    log.info("certification-nomenclature HTTP trigger starting");
    return request.createResponseBuilder(HttpStatus.OK)
        .header("Content-Type", "application/json")
        .body(objectMapper.writeValueAsString(mdmService.getCertificationNomenclature()))
        .build();
  }

  @FunctionName("PH-CommodityClass")
  public HttpResponseMessage commodityClass(
      @HttpTrigger(
          name = "req",
          methods = HttpMethod.GET,
          authLevel = AuthorizationLevel.ANONYMOUS
      ) HttpRequestMessage<Optional<String>> request,
      final ExecutionContext context) throws JsonProcessingException {
    log.info("PH-CommodityClass HTTP trigger starting");
    return request.createResponseBuilder(HttpStatus.OK)
        .header("Content-Type", "application/json")
        .body(objectMapper.writeValueAsString(mdmService.getClasses()))
        .build();
  }

  @FunctionName("PH-CommEPPOVariety")
  public HttpResponseMessage commEppoVariety(
      @HttpTrigger(
          name = "req",
          methods = HttpMethod.GET,
          authLevel = AuthorizationLevel.ANONYMOUS
      ) HttpRequestMessage<Optional<String>> request,
      final ExecutionContext context) throws JsonProcessingException {
    log.info("PH-CommEPPOVariety HTTP trigger starting");
    return request.createResponseBuilder(HttpStatus.OK)
        .header("Content-Type", "application/json")
        .body(objectMapper.writeValueAsString(mdmService.getVarieties()))
        .build();
  }

  @FunctionName("PH-CommodityGroup")
  public HttpResponseMessage commodityGroup(
      @HttpTrigger(
          name = "req",
          methods = HttpMethod.GET,
          authLevel = AuthorizationLevel.ANONYMOUS
      ) HttpRequestMessage<Optional<String>> request,
      final ExecutionContext context) throws JsonProcessingException {
    log.info("PH-CommodityGroup HTTP trigger starting");
    return request.createResponseBuilder(HttpStatus.OK)
        .header("Content-Type", "application/json")
        .body(objectMapper.writeValueAsString(mdmService.getCommodityGroups()))
        .build();
  }

  @FunctionName("PH-CommodityGroup-Commodity")
  public HttpResponseMessage commodityGroupCommodity(
      @HttpTrigger(
          name = "req",
          methods = HttpMethod.GET,
          authLevel = AuthorizationLevel.ANONYMOUS
      ) HttpRequestMessage<Optional<String>> request,
      final ExecutionContext context) throws JsonProcessingException {
    log.info("PH-CommodityGroup-Commodity HTTP trigger starting");
    return request.createResponseBuilder(HttpStatus.OK)
        .header("Content-Type", "application/json")
        .body(objectMapper.writeValueAsString(mdmService.getCommodityGroupCommodity()))
        .build();
  }
}
