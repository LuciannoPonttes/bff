package com.porto.bff.conta.timeline.bffcontastimeline.application.service.conta;

import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

public interface GerenciarContaIaasService {


    DadosResponseDto<ContaResponseDto> getConta(
            String xItauAuth,
            String contaId
    );


    DadosResponseDto<ContaSaldoResponseDto> getContaSaldo(
            String xItauAuth,
            String contaId
    );


    DadosResponseDto<ContaSumarioResponseDto> contaSumario(
            String tokenCognito,
            String xItauAuth,
            String contaId
    );

    void apagarConta(String xItauAuth,
                     String contaId,
                     RequestDeleteDto request);

    void editarStatusConta(
            String xItauAuth,
            String contaId,
            ContaRequestDto requestDto
    );
}
