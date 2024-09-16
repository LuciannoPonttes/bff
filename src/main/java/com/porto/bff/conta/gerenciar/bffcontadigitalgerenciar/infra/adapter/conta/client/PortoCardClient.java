package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.conta.client;


import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.summary.v2.out.response.PortoCardResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@FeignClient(
        value = "portoCartoesGetCardsByUser",
        url = "${feign.client.config.porto.cartao.host}"
)
public interface PortoCardClient {

    @GetMapping("${feign.client.config.porto.cartoes.listar.endpoint}")
    PortoCardResponse getCardsByuser(@RequestHeader(AUTHORIZATION) String tokenCognito);
}
