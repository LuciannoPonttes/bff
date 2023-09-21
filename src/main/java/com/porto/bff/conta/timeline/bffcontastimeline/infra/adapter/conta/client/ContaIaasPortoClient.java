package com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.conta.client;


import com.porto.bff.conta.timeline.bffcontastimeline.domain.model.conta.ContaSaldoDomain;
import com.porto.bff.conta.timeline.bffcontastimeline.domain.model.conta.DadosContaDomain;
import com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.conta.response.AccountResponseIaasPorto;
import com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.conta.response.DataResponseIassPorto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@FeignClient(
        value = "contaIaasFindByIdPortoClient",
        url = "${feign.client.config.porto.host}"
//        configuration = { TokenSensediaInterceptor.class }
)
public interface ContaIaasPortoClient {


    @GetMapping("${feign.client.config.porto.gerenciar.contas.endpoint}")
    DataResponseIassPorto<AccountResponseIaasPorto> findByIdContaIaas(@RequestHeader(AUTHORIZATION) String xItauAuth,
                                                                    @RequestHeader("x-accountProvider") String xAccountProvider,
                                                                    @RequestHeader("x-account-id") String xAccountId,
                                                                    @PathVariable("accountId") String contaId,
                                                                    @RequestParam(required = false) String campos);


    @DeleteMapping("${feign.client.config.porto.gerenciar.contas.endpoint}")
    void deleteByIdContaIaas(@RequestHeader(AUTHORIZATION) String xItauAuth,
                             @RequestHeader("x-accountProvider") String xAccountProvider,
                             @RequestHeader("x-account-id") String xAccountId,
                             @RequestHeader("x-external-id") String xExternalId,
                             @PathVariable("accountId") String contaId);


    @GetMapping("${feign.client.config.porto.gerenciar.contas.saldo.endpoint}")
    DadosContaDomain<ContaSaldoDomain> findBySaldoContaIaas(@RequestHeader(AUTHORIZATION) String xItauAuth,
                                                            @RequestHeader("x-accountProvider") String xAccountProvider,
                                                            @RequestHeader("x-account-id") String xAccountId,
                                                            @PathVariable("accountId") String contaId);
}
