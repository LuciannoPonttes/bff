package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.summary.v2.out.client;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.summary.v2.out.response.PortoCardResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@FeignClient(
        value = "GetCardsByUser",
        url = "${feign.client.config.porto.cartao.host}"
)
public interface CardClient {

    @GetMapping("${feign.client.config.porto.cartoes.listar.endpoint}")
    PortoCardResponse getCardsByuser(@RequestHeader(AUTHORIZATION) String tokenCognito);
}
