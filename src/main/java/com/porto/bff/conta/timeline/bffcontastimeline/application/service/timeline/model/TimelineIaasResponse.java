package com.porto.bff.conta.timeline.bffcontastimeline.application.service.timeline.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TimelineIaasResponse {

    private String onboardingId;
    private String provider;
    private String status;
}
