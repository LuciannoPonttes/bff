package com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.timeline.client;


import com.porto.bff.conta.timeline.bffcontastimeline.application.service.timeline.model.TimelineIaasResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@FeignClient(
        value = "timelineIaasPortoClient",
        url = "${feign.client.config.porto.host}"
//        configuration = { TokenSensediaInterceptor.class }
)
public interface TimelineIaasPortoClient {

    @GetMapping("${feign.client.config.porto.timeline.endpoint}")
    TimelineIaasResponse getTimelineIaas(@RequestHeader(AUTHORIZATION) String tokenCognito,
                                         @RequestHeader("x-account-id") String xAccountId,
                                         @RequestHeader("x-product-id") String xItauAuth,
                                         @PathVariable("accountId") String accountId,
                                         @RequestParam(required = false) Integer perPage,
                                         @RequestParam(required = false) String afterId,
                                         @RequestParam(required = false) String beginDate,
                                         @RequestParam(required = false) String endDate);
}
