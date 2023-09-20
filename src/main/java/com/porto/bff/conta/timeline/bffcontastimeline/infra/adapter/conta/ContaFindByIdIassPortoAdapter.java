package com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.conta;

import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto.ContaResponseDto;
import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto.DadosResponseDto;

public interface ContaFindByIdIassPortoAdapter {

    DadosResponseDto<ContaResponseDto> getConta(String tokenCognito,
                                                String xAccountProvider,
                                                String xAccountId,
                                                String contaId,
                                                String campos);
}
