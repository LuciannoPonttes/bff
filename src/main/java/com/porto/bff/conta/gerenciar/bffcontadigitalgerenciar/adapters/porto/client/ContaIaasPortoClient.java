package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.porto.client;


import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.porto.*;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@FeignClient(
        value = "contaIaasFindByIdPortoClient",
        url = "${feign.client.config.porto.host}"
)
public interface ContaIaasPortoClient {


    @GetMapping("${feign.client.config.porto.gerenciar.contas.endpoint}")
    DataResponseIassPorto<AccountResponseIaasPorto> findByIdContaIaas(@RequestHeader(AUTHORIZATION) String xItauAuth,
                                                                      @RequestHeader("x-accountProvider") String xAccountProvider,
                                                                      @RequestHeader("x-account-id") String xAccountId,
                                                                      @PathVariable("accountId") String accountId);


    @GetMapping("${feign.client.config.porto.gerenciar.contas.saldo.endpoint}")
    DataResponseIassPorto<BalanceResponseIaasPorto> findBySaldoContaIaas(@RequestHeader(AUTHORIZATION) String xItauAuth,
                                                                         @RequestHeader("x-accountProvider") String xAccountProvider,
                                                                         @RequestHeader("x-account-id") String xAccountId,
                                                                         @PathVariable("accountId") String accountId);


    @DeleteMapping("${feign.client.config.porto.gerenciar.contas.endpoint}")
    void deleteByIdContaIaas(@RequestHeader(AUTHORIZATION) String xItauAuth,
                             @RequestHeader("x-accountProvider") String xAccountProvider,
                             @PathVariable("accountId") String accountId,
                             @RequestBody RequestDeleteDto request);

    @RequestMapping(value = "${feign.client.config.porto.gerenciar.contas.endpoint}", method = RequestMethod.PATCH)
    void editarStatusContaIaas(@RequestHeader(AUTHORIZATION) String xItauAuth,
                               @RequestHeader("x-accountProvider") String xAccountProvider,
                               @PathVariable(value = "accountId") String contaId,
                               @RequestBody ContaRequestDto requestDto);
}
