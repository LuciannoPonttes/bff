package com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.conta;

import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto.ContaResponseDto;
import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto.DadosResponseDto;

public interface ContaFindByIdIassPortoAdapter {

    DadosResponseDto<ContaResponseDto> getConta(String xItauAuth,
                                                String xAccountProvider,
                                                String xAccountId,
                                                String contaId,
                                                String campos);

    void apagarConta(String xAccountId,
                     String xItauAuth,
                     String xExternalId,
                     String contaId);
}
