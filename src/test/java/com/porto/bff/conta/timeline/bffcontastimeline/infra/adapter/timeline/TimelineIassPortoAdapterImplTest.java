package com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.timeline;

import com.porto.bff.conta.timeline.bffcontastimeline.application.service.timeline.model.TimelineIaasResponse;
import com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.timeline.client.TimelineIaasPortoClient;
import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.exception.TimelineIaasPortoException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@MockitoSettings
class TimelineIassPortoAdapterImplTest {


    @Mock
    private  TimelineIaasPortoClient client;

    @InjectMocks
    private TimelineIassPortoAdapterImpl timelineIassPortoAdapter;

    @Test
    public  void deveValidarGetTokenIaaS() {

        when(client.getTimelineIaas(
                anyString(),
                anyString(), anyString(),
                anyString(), any(),
                any(), any(), any()
        )).thenThrow(new TimelineIaasPortoException("Problema gerando timeline na consulta da Porto",
                "407", null));

        TimelineIaasPortoException respostaTeste = assertThrows(TimelineIaasPortoException.class,() -> timelineIassPortoAdapter
                .getTimelineIaasResponse("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI5OWY4MTk2Mi1mMjY1LTRiOGEtYTE3ZC0wZTRjYjBmMGJhMzMiLCJldmVudF9pZCI6ImFjNjM3OWFmLTcyYzItNDU1YS1iMWQ1LTBkOTllMjQ5NzI4YyIsInRva2VuX3VzZSI6ImFjY2VzcyIsInNjb3BlIjoiYXdzLmNvZ25pdG8uc2lnbmluLnVzZXIuYWRtaW4iLCJhdXRoX3RpbWUiOjE2MDI4NTkzMzIsImlzcyI6Imh0dHBzOi8vY29nbml0by1pZHAudXMtZWFzdC0xLmFtYXpvbmF3cy5jb20vdXMtZWFzdC0xX2lXeHVkSFpWQSIsImV4cCI6MTYwMjg2MjkzMiwiaWF0IjoxNjAyODU5MzMyLCJqdGkiOiJmMzQ3ZGQ0ZC05ZTQ4LTRmNDEtODUwZS02MzE4NGEyM2E3MGEiLCJjbGllbnRfaWQiOiIyN2NvcDg5bGZncDFsOHFlaTg3aDVlMGw5bCIsInVzZXJuYW1lIjoiMzM1OTA4MzU4MDAifQ.q3r8GA-NxTBFigccTtrwuLlG6uSAJKeQrF9GNnAIcPs",
                        "1",
                        "1",
                        "1",
                        null,
                        null,
                        null,
                        null
                ));

        assertTrue(respostaTeste.getCode().contains("407"));
        assertTrue(respostaTeste.getMessage().contains("Problema gerando timeline na consulta da Porto"));

    }

    private TimelineIaasResponse getTimelineIaasResponse () {
        var tokenDomain = TimelineIaasResponse.builder()
                .onboardingId("a81ff07a-a0c1-428e-9908-0e8199b228e1")
                .status("InProgress")
                .provider("account-pf")
                .build();

        return tokenDomain;
    }
}