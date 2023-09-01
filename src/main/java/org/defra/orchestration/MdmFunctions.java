package org.defra.orchestration;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import lombok.extern.slf4j.Slf4j;
import org.defra.orchestration.apiclient.IpaffsApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class MdmFunctions {

  private final IpaffsApiClient ipaffsApiClient;

  @Autowired
  public MdmFunctions(IpaffsApiClient ipaffsApiClient) {
    this.ipaffsApiClient = ipaffsApiClient;
  }

  @FunctionName("notify")
  public HttpResponseMessage notify(
      @HttpTrigger(
          name = "req",
          methods = HttpMethod.POST,
          authLevel = AuthorizationLevel.FUNCTION
      ) HttpRequestMessage<Optional<String>> request) {
    log.info("notify HTTP trigger starting");
    return request.createResponseBuilder(HttpStatus.valueOf(ipaffsApiClient.syncCommodityCodes().value())).build();
  }
}
