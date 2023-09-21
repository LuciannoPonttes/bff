package com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.conta;

import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto.ContaRequestDto;
import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto.ContaResponseDto;
import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto.ContaSaldoResponseDto;
import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto.DadosResponseDto;

public interface ContaIassPortoAdapter {

    DadosResponseDto<ContaResponseDto> getConta(String xItauAuth,
                                                String xAccountId,
                                                String contaId,
                                                String campos);




    DadosResponseDto<ContaSaldoResponseDto> getContaSaldo(
                                                         String xItauAuth,
                                                         String xAccountId,
                                                         String contaId);

    void apagarConta(String xAccountId,
                     String xItauAuth,
                     String xExternalId,
                     String contaId);

    void editarStatusConta(
            String xItauAuth,
            String xAccountId,
            String xExternalId,
            String contaId,
            ContaRequestDto requestDto);
}
