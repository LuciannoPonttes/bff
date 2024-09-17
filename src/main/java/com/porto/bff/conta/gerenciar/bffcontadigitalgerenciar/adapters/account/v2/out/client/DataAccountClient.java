package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.account.v2.out.client;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.account.AccountDataEntityResponseDomain;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.balance.BalanceEntityResponseDomain;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.common.utils.v2.HttpUtils;
import com.porto.experiencia.cliente.conta.digital.commons.domain.exception.BusinessException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@FeignClient(value = "AccountDataPortoClient", url = "${feign.client.config.porto.host}")
public interface DataAccountClient {

    @GetMapping("${feign.client.config.rotes.backend.account.endpoint}")
    @Retryable(retryFor = { BusinessException.class }, backoff = @Backoff(delay = 150))
    AccountDataEntityResponseDomain getAccountData(@RequestHeader(AUTHORIZATION) String xItauAuth,
                                                   @RequestHeader(HttpUtils.HTTP_PROVIDER_HEADER) String xAccountProvider,
                                                   @RequestHeader(HttpUtils.HTTP_ACCOUNT_ID_HEADER) String xAccountId,
                                                   @PathVariable(HttpUtils.HTTP_ACCOUNT_ID_PATH_VARIABLE) String accountId,
                                                   @RequestParam(HttpUtils.HTTP_ACCOUNT_FIELDS_PARAM) String fields)throws BusinessException;



    @GetMapping("${feign.client.config.rotes.backend.balance.endpoint}")
    @Retryable(retryFor = { BusinessException.class }, backoff = @Backoff(delay = 150))
    BalanceEntityResponseDomain getBalanceAccount(
            @RequestHeader(AUTHORIZATION) String xItauAuth,
            @RequestHeader(HttpUtils.HTTP_PROVIDER_HEADER) String xAccountProvider,
            @RequestHeader(HttpUtils.HTTP_ACCOUNT_ID_HEADER) String xAccountId,
            @PathVariable(HttpUtils.HTTP_ACCOUNT_ID_PATH_VARIABLE) String accountId
    ) throws BusinessException;



}

