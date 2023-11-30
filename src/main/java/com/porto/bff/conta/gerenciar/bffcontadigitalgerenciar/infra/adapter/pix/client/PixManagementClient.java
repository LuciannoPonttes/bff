package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.pix.client;


import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.DataResponseBFF;
import com.porto.experiencia.cliente.conta.digital.commons.web.model.ApiResponseData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@FeignClient(
        value = "pixManagementClient",
        url = "${feign.client.config.bff.host}"
)
public interface PixManagementClient {

    @GetMapping("${feign.client.config.porto.pix.management.endpoint}")
    ApiResponseData<List<Object>> getPixKeyFromAnAccount(@RequestHeader(value = "x-account-id") String xAccountId,
                                                         @RequestHeader(AUTHORIZATION) String authorization,
                                                         @RequestHeader("x-itau-auth") String xItauAuth);
}
