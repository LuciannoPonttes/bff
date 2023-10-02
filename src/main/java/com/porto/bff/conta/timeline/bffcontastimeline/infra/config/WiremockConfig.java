package com.porto.bff.conta.timeline.bffcontastimeline.infra.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.conta.response.AccountResponseIaasPorto;
import com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.conta.response.BankAccountResponseIassPorto;
import com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.conta.response.ContaSaldoResponseIaasPorto;
import com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.conta.response.DataResponseIassPorto;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
@Slf4j
@Profile("local")
@Configuration
public class WiremockConfig {

    private WireMockServer wireMockServer;
    private ObjectWriter ow;

    @Value("${feign.client.config.porto.timeline.endpoint}")
    private String timelinePortoUrl;

    @Value("${feign.client.config.porto.gerenciar.contas.endpoint}")
    private String contaFindIdPortoUrl;

    @Value("${feign.client.config.porto.gerenciar.contas.saldo.endpoint}")
    private String contaSaldoFindIdPortoUrl;

    @PostConstruct
    private void setUp() {
        try {
            ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            wireMockServer = new WireMockServer(options().port(9988)); //No-args constructor will start on port 8080, no HTTPS
            wireMockServer.start();

            // Stubs
            findIdContaIaaSStub();
            deleteContaIaaSStub();
            saldoContaIaaSStub();
            editarContaIaaSStub();
        } catch (Exception e) {
            log.error("Erro ao rodar WireMock Server. Erro: {}", e.getMessage());
        }
    }




    private void findIdContaIaaSStub() throws JsonProcessingException {
        var contaBancariaDto = new BankAccountResponseIassPorto("Portoseg S.A.", "Porto", "123456", "8");
        var contaResponseDto = new AccountResponseIaasPorto(
                "5386ec67-3d85-47c9-b97c-38129e73c519",
                contaBancariaDto,
                "ATIVO",
                "PF",
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

    private void editarContaIaaSStub() throws JsonProcessingException {
        var contaBancariaDto = new BankAccountResponseIassPorto("Portoseg S.A.", "Porto", "123456", "8");
        var contaResponseDto = new AccountResponseIaasPorto(
                "5386ec67-3d85-47c9-b97c-38129e73c519",
                contaBancariaDto,
                "ATIVO",
                "PF",
                "2023-09-21T18:14:56.868Z",
                "2023-09-21T18:14:56.868Z"

        );
        var respostaFinal  = new DataResponseIassPorto<AccountResponseIaasPorto>(contaResponseDto);
        wireMockServer
                .stubFor(patch(urlEqualTo("/porto-backend/v1/account/1"))
                        .willReturn(aResponse()
                                .withHeader("Content-Type", "application/json")
                                .withBody(ow.writeValueAsString(HttpStatus.ACCEPTED))));
    }

    private void deleteContaIaaSStub() throws JsonProcessingException {
        var contaBancariaDto = new BankAccountResponseIassPorto("Portoseg S.A.", "Porto", "123456", "8");
        var contaResponseDto = new AccountResponseIaasPorto(
                "5386ec67-3d85-47c9-b97c-38129e73c519",
                contaBancariaDto,
                "ATIVO",
                "PF",
                "2023-09-21T18:14:56.868Z",
                "2023-09-21T18:14:56.868Z"

        );
        var respostaFinal  = new DataResponseIassPorto<AccountResponseIaasPorto>(contaResponseDto);
        wireMockServer
                .stubFor(delete(urlEqualTo("/porto-backend/v1/account/1"))
                        .willReturn(aResponse()
                                .withHeader("Content-Type", "application/json")
                                .withBody(ow.writeValueAsString(HttpStatus.ACCEPTED))));
    }

    private void saldoContaIaaSStub() throws JsonProcessingException {

        var contaResponseDto = new ContaSaldoResponseIaasPorto("124657256", 2000, 750, 190);
        var respostaFinal  = new DataResponseIassPorto<ContaSaldoResponseIaasPorto>(contaResponseDto);
        wireMockServer
                .stubFor(get(urlEqualTo("/porto-backend/v1/accounts/1/balances"))
                        .willReturn(aResponse()
                                .withHeader("Content-Type", "application/json")
                                .withBody(ow.writeValueAsString(respostaFinal))));
    }

    @PreDestroy
    private void preDestroy() {
        wireMockServer.stop();
    }



}