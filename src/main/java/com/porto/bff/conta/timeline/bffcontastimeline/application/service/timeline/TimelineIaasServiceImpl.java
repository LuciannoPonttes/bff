package com.porto.bff.conta.timeline.bffcontastimeline.application.service.timeline;

import com.porto.bff.conta.timeline.bffcontastimeline.application.service.timeline.model.TimelineIaasResponse;
import com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.decodertoken.DecodificarAccessToken;
import com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.timeline.TimelineIassPortoAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TimelineIaasServiceImpl implements TimelineIaasService{

    private final TimelineIassPortoAdapter adapter;

    private final DecodificarAccessToken decodificarAccessToken;

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
        decodificarAccessToken.getCpfPorToken(tokenCognito);
        var respostaAdatper = adapter.getTimelineIaasResponse(
                tokenCognito,
                xAccountId,
                xItauAuth,
                accountId,
                perPage,
                afterId,
                beginDate,
                endDate
        );

        return respostaAdatper;
    }
}
