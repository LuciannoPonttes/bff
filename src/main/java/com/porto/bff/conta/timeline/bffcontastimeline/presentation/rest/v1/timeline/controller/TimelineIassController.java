package com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.timeline.controller;

import com.porto.bff.conta.timeline.bffcontastimeline.application.service.timeline.TimelineIaasService;
import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.timeline.dto.TimelineIaasResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequiredArgsConstructor
public class TimelineIassController implements TimelineIassControllerOperations{

    private final TimelineIaasService timelineIaasService;

    @Override
    public ResponseEntity<TimelineIaasResponseDto> geraTimelineIaaS(
            String tokenCognito,
            String xAccountId,
            String xItauAuth,
            String accountId,
            Integer perPage,
            String afterId,
            String beginDate,
            String endDate) {
        var timelineResponse = timelineIaasService.getTimelineIaasResponse(tokenCognito,
                xAccountId,
                xItauAuth,
                accountId,
                perPage,
                afterId,
                beginDate,
                endDate);

            var response = new TimelineIaasResponseDto(timelineResponse.getOnboardingId(),
                    timelineResponse.getProvider(),
                    timelineResponse.getStatus());

            return ResponseEntity.status(HttpStatus.OK).body(response);

    }
}
