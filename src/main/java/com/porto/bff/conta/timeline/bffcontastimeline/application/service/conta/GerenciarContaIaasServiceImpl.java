package com.porto.bff.conta.timeline.bffcontastimeline.application.service.conta;

import com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.conta.ContaIassPortoAdapter;
import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class GerenciarContaIaasServiceImpl implements GerenciarContaIaasService{

    private final ContaIassPortoAdapter adapter;


    @Override
    public DadosResponseDto<ContaResponseDto> getConta(
            String xItauAuth,
            String contaId
    ) {
        var respostaAdapter = adapter.getConta(
                xItauAuth,
                contaId
        );
        return respostaAdapter;
    }



    @Override
    public DadosResponseDto<ContaSaldoResponseDto> getContaSaldo(
            String xItauAuth,
            String xAccountId,
            String contaId) {

        var respostaAdapter = adapter.getContaSaldo(
                 xItauAuth,
                 xAccountId,
                 contaId);
        return respostaAdapter;
    }

    @Override
    public DadosResponseDto<ContaSumarioResponseDto> contaSumario(
            String tokenCognito,
            String xItauAuth,
            String contaId) {

        var respostaAdapter = adapter.sumarioConta(xItauAuth, contaId);
        return respostaAdapter;
    }

    @Override
    public void apagarConta(
                            String xItauAuth,
                            String contaId) {

        adapter.apagarConta(xItauAuth, contaId);

    }

    @Override
    public void editarStatusConta(
            String xItauAuth,
            String contaId,
            ContaRequestDto requestDto) {

        adapter.editarStatusConta(
                 xItauAuth,
                 contaId,
                 requestDto
        );

    }
}
