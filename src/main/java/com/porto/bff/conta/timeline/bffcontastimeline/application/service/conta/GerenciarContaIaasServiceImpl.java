package com.porto.bff.conta.timeline.bffcontastimeline.application.service.conta;

import com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.conta.ContaFindByIdIassPortoAdapter;
import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto.ContaResponseDto;
import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto.DadosResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class GerenciarContaIaasServiceImpl implements GerenciarContaIaasService{

    private final ContaFindByIdIassPortoAdapter adapter;
    @Override
    public DadosResponseDto<ContaResponseDto> getConta(
            String tokenCognito,
            String xAccountProvider,
            String xAccountId,
            String contaId,
            String campos) {
        var respostaAdapter = adapter.getConta(
                tokenCognito,
                xAccountProvider,
                xAccountId,
                contaId,
                campos
        );


        return respostaAdapter;
    }
}
