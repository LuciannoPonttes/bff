package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.account.v2.client;


import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.BackendResponseData;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.saldo.v2.AccountBalanceEntityResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@FeignClient(value = "AccountManagementPortoClient", url = "${feign.client.config.porto.host}")
public interface AccountManagementClient {
    @GetMapping("${feign.client.config.rotes.backend.balance.endpoint}")
    BackendResponseData<AccountBalanceEntityResponse> findBySaldoContaIaas(@RequestHeader(AUTHORIZATION) String xItauAuth,
                                                                           @RequestHeader("x-accountProvider") String xAccountProvider,
                                                                           @RequestHeader("x-account-id") String xAccountId,
                                                                           @PathVariable("accountId") String accountId);
}
