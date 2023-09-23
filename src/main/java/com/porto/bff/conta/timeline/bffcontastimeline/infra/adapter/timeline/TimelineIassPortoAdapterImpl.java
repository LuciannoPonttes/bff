package com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.timeline;


import com.porto.bff.conta.timeline.bffcontastimeline.application.service.timeline.model.TimelineIaasResponse;
import com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.timeline.client.TimelineIaasPortoClient;
import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.exception.TimelineIaasPortoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class TimelineIassPortoAdapterImpl implements TimelineIassPortoAdapter{

    private final TimelineIaasPortoClient client;

    @Override
    public TimelineIaasResponse getTimelineIaasResponse(
            String tokenCognito,
            String xAccountId,
            String xItauAuth,
            String accountId,
            Integer perPage,
            String afterId,
            String beginDate,
            String endDate) {
        try {
            return client.getTimelineIaas(
                    tokenCognito,
                    xAccountId,
                    xItauAuth,
                    accountId,
                    perPage,
                    afterId,
                    beginDate,
                    endDate);
        } catch (Exception e) {
            throw new TimelineIaasPortoException("Problema gerando timeline na consulta da Porto",
                    "407",
                    Collections.singletonList(TimelineIaasPortoException.TimelineIaasPortoErroItem.builder()
                            .field("accessToken")
                            .message("Falha ao gerar accessToken")
                            .build()));
        }
    }
}
