package com.porto.bff.conta.timeline.bffcontastimeline.application.service.conta;

import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto.ContaResponseDto;
import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto.DadosResponseDto;

public interface GerenciarContaIaasService {


    DadosResponseDto<ContaResponseDto> getConta(
            String tokenCognito,
            String xAccountProvider,
            String xAccountId,
            String contaId,
            String campos
    );
}
