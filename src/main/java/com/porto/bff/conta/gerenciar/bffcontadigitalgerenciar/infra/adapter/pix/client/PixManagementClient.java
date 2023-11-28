package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.pix.client;


import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.DataResponseIassPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.pix.ListChavePixResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@FeignClient(
        value = "pixManagementClient",
        url = "${feign.client.config.porto.host}"
)
public interface PixManagementClient {

    @GetMapping("${feign.client.config.porto.pix.management.endpoint}")
    DataResponseIassPorto<List<ListChavePixResponse>> getPixKeyFromAnAccount(
            @RequestHeader(value = "x-account-id") String xAccountId,
            @RequestHeader("x-accountProvider") String xaccountProvider,
            @RequestHeader(AUTHORIZATION) String authorization);
}
