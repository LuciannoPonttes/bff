package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.balance.v2.out.client;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.balance.BalanceEntityResponseDomain;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.common.utils.v2.HttpUtils;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@FeignClient(value = "BalanceDataPortoClient", url = "${feign.client.config.porto.host}")
public interface DataBalanceClient {

    @GetMapping("${feign.client.config.rotes.backend.balance.endpoint}")
    BalanceEntityResponseDomain getBalanceAccount(@RequestHeader(AUTHORIZATION) String xItauAuth,
                                                  @RequestHeader(HttpUtils.HTTP_PROVIDER_HEADER) String xAccountProvider,
                                                  @RequestHeader(HttpUtils.HTTP_ACCOUNT_ID_HEADER) String xAccountId,
                                                  @PathVariable(HttpUtils.HTTP_ACCOUNT_ID_PATH_VARIABLE) String accountId);
}
