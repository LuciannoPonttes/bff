package com.porto.bff.conta.timeline.bffcontastimeline.application.service.conta;

import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto.ContaResponseDto;
import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto.DadosResponseDto;

public interface GerenciarContaIaasService {


    DadosResponseDto<ContaResponseDto> getConta(
            String tokenCognito,
            String xAccountProvider,
            String xItauAuth,
            String xAccountId,
            String contaId,
            String campos
    );
    void apagarConta(String tokenCognito,
                     String xAccountId,
                     String xItauAuth,
                     String xExternalId,
                     String contaId);
}
