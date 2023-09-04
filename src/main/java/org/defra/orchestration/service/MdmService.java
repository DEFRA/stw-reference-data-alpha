package org.defra.orchestration.service;

import java.util.List;
import org.defra.orchestration.apiclient.MdmApiClient;
import org.defra.orchestration.apiclient.model.Commodity;
import org.defra.orchestration.dto.Certificate;
import org.defra.orchestration.dto.CommodityNomenclature;
import org.defra.orchestration.dto.DataEntity;
import org.defra.orchestration.dto.Meta;
import org.defra.orchestration.dto.Pages;
import org.defra.orchestration.dto.RdsResponse;
import org.defra.orchestration.mapper.CertificateMapper;
import org.defra.orchestration.mapper.CommodityNomenclatureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MdmService {

  private final MdmApiClient apiClient;
  private final CommodityNomenclatureMapper commodityNomenclatureMapper;
  private final CertificateMapper certificateMapper;

  @Autowired
  public MdmService(
      MdmApiClient apiClient,
      CommodityNomenclatureMapper commodityNomenclatureMapper,
      CertificateMapper certificateMapper) {
    this.apiClient = apiClient;
    this.commodityNomenclatureMapper = commodityNomenclatureMapper;
    this.certificateMapper = certificateMapper;
  }

  public RdsResponse<CommodityNomenclature> getCommodityNomenclature() {
    List<Commodity> commodities = apiClient.getCommodities();
    List<CommodityNomenclature> data = commodities.stream()
        .map(Commodity::getCommodityCode)
        .distinct()
        .map(commodityNomenclatureMapper::map)
        .toList();
    return buildResponse(data);
  }

  public RdsResponse<Certificate> getCertificates() {
    List<Commodity> commodities = apiClient.getCommodities();
    List<Certificate> data = commodities.stream()
        .map(Commodity::getCertificate)
        .distinct()
        .map(certificateMapper::map)
        .toList();
    return buildResponse(data);
  }

  private <T extends DataEntity> RdsResponse<T> buildResponse(List<T> data) {
    return RdsResponse.<T>builder()
        .meta(Meta.builder()
            .count(data.size())
            .total(data.size())
            .pages(Pages.builder().build())
            .build())
        .data(data)
        .build();
  }
}
