package com.porto.bff.conta.timeline.bffcontastimeline.application.service.conta;

import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto.ContaResponseDto;
import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto.ContaSaldoResponseDto;
import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto.DadosResponseDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

public interface GerenciarContaIaasService {


    DadosResponseDto<ContaResponseDto> getConta(
            String xItauAuth,
            String xAccountId,
            String contaId,
            String campos
    );


    DadosResponseDto<ContaSaldoResponseDto> getContaSaldo(
            String xItauAuth,
            String xAccountId,
            String contaId
    );

    void apagarConta(String xItauAuth,
                     String xAccountId,
                     String xExternalId,
                     String contaId);
}
