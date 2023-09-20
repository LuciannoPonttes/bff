package com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.conta.client;


import com.porto.bff.conta.timeline.bffcontastimeline.application.service.timeline.model.TimelineIaasResponse;
import com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.conta.response.AccountResponseIaasPorto;
import com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.conta.response.DataResponseIassPorto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@FeignClient(
        value = "contaIaasFindByIdPortoClient",
        url = "${feign.client.config.porto.host}"
//        configuration = { TokenSensediaInterceptor.class }
)
public interface ContaIaasFindByIdPortoClient {


    @GetMapping("${feign.client.config.porto.gerenciar.id.endpoint}")
    DataResponseIassPorto<AccountResponseIaasPorto> findByIdContaIaas(@RequestHeader(AUTHORIZATION) String xItauAuth,
                                                                    @RequestHeader("x-accountProvider") String xAccountProvider,
                                                                    @RequestHeader("x-account-id") String xAccountId,
                                                                    @PathVariable("accountId") String contaId,
                                                                    @RequestParam(required = false) String campos);
}
