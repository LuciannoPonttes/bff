package com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.conta;


import com.porto.bff.conta.timeline.bffcontastimeline.domain.model.conta.ContaSaldoDomain;
import com.porto.bff.conta.timeline.bffcontastimeline.domain.model.conta.DadosContaDomain;
import com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.conta.client.ContaIaasPortoClient;
import com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.conta.response.AccountResponseIaasPorto;
import com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.conta.response.DataResponseIassPorto;
import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto.*;
import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.exception.TimelineIaasPortoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class ContaIassPortoAdapterImpl implements ContaIassPortoAdapter {

    private final ContaIaasPortoClient client;


    @Override
    public DadosResponseDto<ContaResponseDto> getConta(
            String xItauAuth,
            String xAccountId,
            String contaId,
            String campos) {

        try {
            var responseIaas = client.findByIdContaIaas(
                    xItauAuth,
                    "IAAS",
                    xAccountId,
                    contaId,
                    campos
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
    public void apagarConta(String xAccountId,
                            String xItauAuth,
                            String xExternalId,
                            String contaId) {
        try {
          client.deleteByIdContaIaas(
                    xItauAuth,
                  "IAAS",
                    xAccountId,
                    xExternalId,
                    contaId
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
            String xAccountId,
            String xExternalId,
            String contaId,
            ContaRequestDto requestDto) {

        try {
//            client.editarStatusContaIaas(
//                    xItauAuth,
//                    "IAAS",
//                    xAccountId,
//                    xExternalId,
//                    contaId,
//                    requestDto
//            );
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
            String xAccountId,
            String contaId) {


        try {
            var responseIaas = client.findBySaldoContaIaas(
                    xItauAuth,
                    "IAAS",
                    xAccountId,
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
            var conta = getConta(xItauAuth, contaId, contaId, null);
            var contaSaldo = getContaSaldo(xItauAuth,contaId, contaId);

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

    private DadosResponseDto<ContaSaldoResponseDto> converteRespostaContaSaldoIaas(DadosContaDomain<ContaSaldoDomain> porto) {
        var contaSaldoDto = new ContaSaldoResponseDto(
                porto.dados().getAccountId(),
                porto.dados().getAvailable(),
                porto.dados().getReserved(),
                porto.dados().getBlocked()
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
}
