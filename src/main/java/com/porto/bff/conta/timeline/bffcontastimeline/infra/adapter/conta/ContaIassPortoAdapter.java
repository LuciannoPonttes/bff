package com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.conta;

import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto.*;

public interface ContaIassPortoAdapter {

    DadosResponseDto<ContaResponseDto> getConta(String xItauAuth,
                                                String contaId);




    DadosResponseDto<ContaSaldoResponseDto> getContaSaldo(
                                                         String xItauAuth,
                                                         String xAccountId,
                                                         String contaId);


    DadosResponseDto<ContaSumarioResponseDto> sumarioConta(String xItauAuth,
                                                           String contaId);


    void apagarConta(String xItauAuth,
                     String contaId,
    RequestDeleteDto request);

    void editarStatusConta(
            String xItauAuth,
            String contaId,
            ContaRequestDto requestDto);
}
