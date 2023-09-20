package com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.conta;


import com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.conta.client.ContaIaasFindByIdPortoClient;
import com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.conta.response.AccountResponseIaasPorto;
import com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.conta.response.DataResponseIassPorto;
import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto.ContaBancariaDto;
import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto.ContaResponseDto;
import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto.DadosResponseDto;
import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.exception.TimelineIaasPortoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class ContaFindByIdIassPortoAdapterImpl implements ContaFindByIdIassPortoAdapter{

    private final ContaIaasFindByIdPortoClient client;


    @Override
    public DadosResponseDto<ContaResponseDto> getConta(
            String xItauAuth,
            String xAccountProvider,
            String xAccountId,
            String contaId,
            String campos) {

        try {
            var responseIaas = client.findByIdContaIaas(
                    xItauAuth,
                    xAccountProvider,
                    xAccountId,
                    contaId,
                    campos
            );
            var reponse = converteRespostaIaas(responseIaas);
            return reponse;
        } catch (Exception e) {
            throw new TimelineIaasPortoException("Problema gerando no gerenciamento da consulta da Porto",
                    "407",
                    Collections.singletonList(TimelineIaasPortoException.TimelineIaasPortoErroItem.builder()
                            .field("accessToken")
                            .message("Falha ao gerar accessToken")
                            .build()));
        }
    }

    private DadosResponseDto<ContaResponseDto> converteRespostaIaas(DataResponseIassPorto<AccountResponseIaasPorto> porto) {
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
