package com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.conta;


import com.porto.bff.conta.timeline.bffcontastimeline.domain.model.conta.DadosContaDomain;
import com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.conta.client.ContaIaasPortoClient;
import com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.conta.response.AccountResponseIaasPorto;
import com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.conta.response.ContaSaldoResponseIaasPorto;
import com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.conta.response.DataResponseIassPorto;
import com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.conta.response.saldo.AccountData;
import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto.*;
import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.exception.TimelineIaasPortoException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
@RequiredArgsConstructor
public class ContaIassPortoAdapterImpl implements ContaIassPortoAdapter {

    private final ContaIaasPortoClient client;
    private static String BEARER = "Bearer ";

    @Value("${feign.client.config.porto.gerenciar.contas.saldo.endpoint}")
    private String contaSaldoFindIdPortoUrl;

    @Override
    public DadosResponseDto<ContaResponseDto> getConta(
            String xItauAuth,
            String contaId) {

        try {
            var responseIaas = client.findByIdContaIaas(
                    getBearerInput(xItauAuth), "IAAS",
                    contaId
            );
            var reponse = converteRespostaContaIaas(responseIaas);
            return reponse;
        } catch (Exception e) {
            throw new TimelineIaasPortoException("Problema gerando ao consultar dados da conta Porto.",
                    "407",
                    Collections.singletonList(TimelineIaasPortoException.TimelineIaasPortoErroItem.builder()
                            .field("accessToken")
                            .message("Falha ao gerar accessToken")
                            .build()));
        }
    }

    @Override
    public void apagarConta(String xItauAuth,
                            String contaId,
                            RequestDeleteDto request) {
        try {
          client.deleteByIdContaIaas(
                    xItauAuth,
                  "IAAS",
                    contaId,
                  request
            );
        } catch (Exception e) {
            throw new TimelineIaasPortoException("Problema gerando apagar conta Porto",
                    "407",
                    Collections.singletonList(TimelineIaasPortoException.TimelineIaasPortoErroItem.builder()
                            .field("accessToken")
                            .message("Falha ao gerar accessToken")
                            .build()));
        }
    }

    @Override
    public void editarStatusConta(
            String xItauAuth,
            String contaId,
            ContaRequestDto requestDto) {

        try {
            client.editarStatusContaIaas(
                    getBearerInput(xItauAuth),
                    "IAAS",
                    contaId,
                    requestDto
            );

        } catch (Exception e) {
            throw new TimelineIaasPortoException("Problema gerando no editar status da conta Porto",
                    "407",
                    Collections.singletonList(TimelineIaasPortoException.TimelineIaasPortoErroItem.builder()
                            .field("accessToken")
                            .message("Falha ao gerar accessToken")
                            .build()));
        }


    }

    @Override
    public DadosResponseDto<ContaSaldoResponseDto> getContaSaldo(
            String xItauAuth,
            String contaId) {


        try {
            var responseIaas = client.findBySaldoContaIaas(
                    getBearerInput(xItauAuth),
                    contaId
            );
             var reponse = converteRespostaContaSaldoIaas(responseIaas);
            return reponse;
        } catch (Exception e) {
            throw new TimelineIaasPortoException("Problema gerando no gerenciamento da consulta do buscar saldo da Porto",
                    "407",
                    Collections.singletonList(TimelineIaasPortoException.TimelineIaasPortoErroItem.builder()
                            .field("accessToken")
                            .message("Falha ao gerar accessToken")
                            .build()));
        }
    }

    @Override
    public DadosResponseDto<ContaSumarioResponseDto> sumarioConta(String xItauAuth, String contaId) {


        try {
            var conta = getConta( getBearerInput(xItauAuth), contaId);
            var contaSaldo = getContaSaldo( getBearerInput(xItauAuth),contaId);

            DadosResponseDto<ContaSumarioResponseDto> reponse = getSumarioContaConverte(conta, contaSaldo);
            return reponse;
        } catch (Exception e) {
            throw new TimelineIaasPortoException("Problema gerando no gerenciamento da consulta do buscar saldo da Porto",
                    "407",
                    Collections.singletonList(TimelineIaasPortoException.TimelineIaasPortoErroItem.builder()
                            .field("accessToken")
                            .message("Falha ao gerar accessToken")
                            .build()));
        }
    }

    private DadosResponseDto<ContaSumarioResponseDto> getSumarioContaConverte(
            DadosResponseDto<ContaResponseDto> conta,
            DadosResponseDto<ContaSaldoResponseDto> contaSaldo) {

        ContaSumarioResponseDto contaSumarioResponseDto = new ContaSumarioResponseDto(
                conta.dados().contaBancaria().banco(),
                conta.dados().contaBancaria().filial(),
                conta.dados().contaBancaria().numero(),
                conta.dados().contaBancaria().numero(),
                String.valueOf(contaSaldo.dados().disponivel()),
                conta.dados().status()
                );

        return new DadosResponseDto(contaSumarioResponseDto);
    }

    private DadosResponseDto<ContaSaldoResponseDto> converteRespostaContaSaldoIaas(AccountData porto) {
       var limit =  porto.getData().getAccount().getLimits().stream()
               .findFirst().stream().collect(Collectors.toList());

        var contaSaldoDto = new ContaSaldoResponseDto(
                limit.get(0).getId(),
                limit.get(0).getAvailableAmount(),
                limit.get(0).getCustomerChosenAmount(),
                limit.get(0).getMinDefaultValue(),
                limit.get(0).getMaxDefaultValue()
        );

        var contaSaldo = new DadosResponseDto<>(contaSaldoDto);

        return contaSaldo;
    }

    private DadosResponseDto<ContaResponseDto> converteRespostaContaIaas(DataResponseIassPorto<AccountResponseIaasPorto> porto) {
        var contaBancariaDto = new ContaBancariaDto(
                porto.data().bankAccount().bank(),
                porto.data().bankAccount().branch(),
                porto.data().bankAccount().number(),
                porto.data().bankAccount().checkDigit()
                );
        var contaResponseDto = new ContaResponseDto(
                porto.data().id(),
                contaBancariaDto,
                porto.data().status(),
                porto.data().type(),
                porto.data().createdAt(),
                porto.data().updatedAt()
                );

        return new DadosResponseDto(contaResponseDto);
    }

    private String getBearerInput(String xItauAuth) {
        if (xItauAuth.contains("Bearer")) {
            return xItauAuth;
        }
        var resposta = BEARER+xItauAuth;
        return resposta;
    }
}
