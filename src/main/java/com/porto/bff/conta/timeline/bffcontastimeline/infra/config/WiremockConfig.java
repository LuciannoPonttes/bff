package com.porto.bff.conta.timeline.bffcontastimeline.infra.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.porto.bff.conta.timeline.bffcontastimeline.application.service.timeline.model.TimelineIaasResponse;
import com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.conta.response.AccountResponseIaasPorto;
import com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.conta.response.BankAccountResponseIassPorto;
import com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.conta.response.DataResponseIassPorto;
import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto.ContaBancariaDto;
import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto.ContaResponseDto;
import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto.DadosResponseDto;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
@Slf4j
@Profile("local")
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

    @Value("${feign.client.config.porto.gerenciar.contas.endpoint}")
    private String contaFindIdPortoUrl;


    @PostConstruct
    private void setUp() {
        try {
            ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            wireMockServer = new WireMockServer(options().port(9988)); //No-args constructor will start on port 8080, no HTTPS
            wireMockServer.start();

            // Stubs
            timelineIaaSStub();
            findIdContaIaaSStub();
        } catch (Exception e) {
            log.error("Erro ao rodar WireMock Server. Erro: {}", e.getMessage());
        }
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

    private void findIdContaIaaSStub() throws JsonProcessingException {
        var contaBancariaDto = new BankAccountResponseIassPorto("itau", "itau", "123", "1");
        var contaResponseDto = new AccountResponseIaasPorto(
                "5386ec67-3d85-47c9-b97c-38129e73c519",
                contaBancariaDto,
                "ATIVO",
                "ATIVO",
                "2023-09-21T18:14:56.868Z",
                "2023-09-21T18:14:56.868Z"

                );
        var respostaFinal  = new DataResponseIassPorto<AccountResponseIaasPorto>(contaResponseDto);
        wireMockServer
                .stubFor(get(urlEqualTo("/porto-backend/v1/account/1"))
                        .willReturn(aResponse()
                                .withHeader("Content-Type", "application/json")
                                .withBody(ow.writeValueAsString(respostaFinal))));
    }

    @PreDestroy
    private void preDestroy() {
        wireMockServer.stop();
    }



}