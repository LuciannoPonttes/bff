package com.porto.bff.conta.timeline.bffcontastimeline.application.service.conta;

import com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.conta.ContaIassPortoAdapter;
import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto.*;
import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.exception.TimelineIaasPortoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
@RequiredArgsConstructor
public class GerenciarContaIaasServiceImpl implements GerenciarContaIaasService{

    private final ContaIassPortoAdapter adapter;


    @Override
    public DadosResponseDto<ContaResponseDto> getConta(
            String xItauAuth,
            String contaId
    ) {
        isValidHeader(xItauAuth, contaId);
        var respostaAdapter = adapter.getConta(
                xItauAuth,
                contaId
        );
        return respostaAdapter;
    }



    @Override
    public DadosResponseDto<ContaSaldoResponseDto> getContaSaldo(
            String xItauAuth,
            String contaId) {

        isValidHeader(xItauAuth, contaId);
        var respostaAdapter = adapter.getContaSaldo(
                 xItauAuth,
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
                            String contaId,
                            RequestDeleteDto request) {
        isValidHeader(xItauAuth, contaId);
        adapter.apagarConta(xItauAuth, contaId, request);

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

    private boolean isValidHeader(String xItauAuth, String contaId) {
        List<TimelineIaasPortoException.TimelineIaasPortoErroItem> errors = new ArrayList<>();
        try {
            if(xItauAuth.isEmpty() || contaId.isEmpty()) {
                throw new TimelineIaasPortoException("Campos obrigatorios", "407", errors);
            } else {
                return true;
            }
        }catch (Exception e) {
            throw new TimelineIaasPortoException("Campos obrigatorios", "407", errors);
        }
    }
}
