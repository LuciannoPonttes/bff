package com.porto.bff.conta.timeline.bffcontastimeline.infra.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.porto.bff.conta.timeline.bffcontastimeline.application.service.timeline.model.TimelineIaasResponse;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
@Slf4j
//@Profile("local")
@Configuration
public class WiremockConfig {

    private WireMockServer wireMockServer;
    private ObjectWriter ow;

    @Value("${feign.client.config.porto.pareamento.endpoint}")
    private String pareamentoPortoUrl;

    @Value("${feign.client.config.porto.token.endpoint}")
    private String tokenIaaSPortoUrl;

    @Value("${feign.client.config.porto.elegivel.endpoint}")
    private String elegivelPortoUrl;


    @Value("${feign.client.config.porto.timeline.endpoint}")
    private String timelinePortoUrl;


    @PostConstruct
    private void setUp() {
        try {
            ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            wireMockServer = new WireMockServer(options().port(9988)); //No-args constructor will start on port 8080, no HTTPS
            wireMockServer.start();

            // Stubs
            timelineIaaSStub();

        } catch (Exception e) {
            log.error("Erro ao rodar WireMock Server. Erro: {}", e.getMessage());
        }
    }

    @PreDestroy
    private void preDestroy() {
        wireMockServer.stop();
    }
    private void timelineIaaSStub() throws JsonProcessingException {
        var timelineResponse = TimelineIaasResponse.builder()
                .onboardingId("a81ff07a-a0c1-428e-9908-0e8199b228e1")
                .provider("account-pf")
                .status("InProgress")
                .build();
        wireMockServer
                .stubFor(get(urlEqualTo("/porto-backend/v1/accounts/1/search"))
                        .willReturn(aResponse()
                                .withHeader("Content-Type", "application/json")
                                .withBody(ow.writeValueAsString(timelineResponse))));
    }

}