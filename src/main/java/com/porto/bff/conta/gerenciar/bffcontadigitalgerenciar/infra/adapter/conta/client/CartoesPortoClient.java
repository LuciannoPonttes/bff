package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.conta.client;


import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.cartoes.ListaCartoesResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@FeignClient(
        value = "portoCartoesGetCardsByuser",
        url = "${feign.client.config.porto.cartao.host}"
)
public interface CartoesPortoClient {


    @GetMapping("${feign.client.config.porto.cartoes.listar.endpoint}")
    ListaCartoesResponse getCardsByuser(@RequestHeader(AUTHORIZATION) String tokenCognito);
}
