package com.porto.bff.conta.timeline.bffcontastimeline.application.service.timeline;

import com.porto.bff.conta.timeline.bffcontastimeline.application.service.timeline.model.TimelineIaasResponse;
import com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.decodertoken.DecodificarAccessToken;
import com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.timeline.TimelineIassPortoAdapter;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@MockitoSettings
class TimelineIaasServiceImplTest {

    @Mock
    private TimelineIassPortoAdapter adapter;

    @Mock
    private DecodificarAccessToken decodificarAccessToken;

    @InjectMocks
    private  TimelineIaasServiceImpl timelineIaasService;


    @Test
    void deveRetornarAToken() {
//        TokenIaasRequestDto requestDto = new TokenIaasRequestDto("", Collections.singletonList("kyc.onboarding"));
        TimelineIaasResponse tokenDomain = TimelineIaasResponse.builder()
                .onboardingId("a81ff07a-a0c1-428e-9908-0e8199b228e1")
                .status("InProgress")
                .provider("account-pf")
                .build();
        String accessToken = "some-access-token";

        when(adapter.getTimelineIaasResponse(anyString(),
                anyString(), anyString(),
                anyString(), any(),
                any(), any(), any())).thenReturn(tokenDomain);

        TimelineIaasResponse response = timelineIaasService.getTimelineIaasResponse(
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI5OWY4MTk2Mi1mMjY1LTRiOGEtYTE3ZC0wZTRjYjBmMGJhMzMiLCJldmVudF9pZCI6ImFjNjM3OWFmLTcyYzItNDU1YS1iMWQ1LTBkOTllMjQ5NzI4YyIsInRva2VuX3VzZSI6ImFjY2VzcyIsInNjb3BlIjoiYXdzLmNvZ25pdG8uc2lnbmluLnVzZXIuYWRtaW4iLCJhdXRoX3RpbWUiOjE2MDI4NTkzMzIsImlzcyI6Imh0dHBzOi8vY29nbml0by1pZHAudXMtZWFzdC0xLmFtYXpvbmF3cy5jb20vdXMtZWFzdC0xX2lXeHVkSFpWQSIsImV4cCI6MTYwMjg2MjkzMiwiaWF0IjoxNjAyODU5MzMyLCJqdGkiOiJmMzQ3ZGQ0ZC05ZTQ4LTRmNDEtODUwZS02MzE4NGEyM2E3MGEiLCJjbGllbnRfaWQiOiIyN2NvcDg5bGZncDFsOHFlaTg3aDVlMGw5bCIsInVzZXJuYW1lIjoiMzM1OTA4MzU4MDAifQ.q3r8GA-NxTBFigccTtrwuLlG6uSAJKeQrF9GNnAIcPs",
                "1",
                "1",
                "1",
                null,
                null,
                null,
                null
        );

        assertNotNull(response);
        assertNotNull(response.getOnboardingId());
        assertNotNull(response.getProvider());
        assertNotNull(response.getStatus());
    }


}