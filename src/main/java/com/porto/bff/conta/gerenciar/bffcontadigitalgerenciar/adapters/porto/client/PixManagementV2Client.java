package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.porto.client;


import com.porto.experiencia.cliente.conta.digital.commons.web.model.ApiResponseData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@FeignClient(
        value = "PixManagementV2Client",
        url = "${feign.client.config.bff.host}"
)
public interface PixManagementV2Client {

    @GetMapping("${feign.client.config.porto.pix.management.endpoint.v2}")
    ApiResponseData<List<Object>> getPixKeyFromAnAccount(@RequestHeader(AUTHORIZATION) String authorization,
                                                         @RequestHeader("x-itau-auth") String xItauAuth,
                                                         @RequestHeader(value = "x-account-id") String xAccountId);
}
