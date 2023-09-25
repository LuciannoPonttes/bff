package com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.controller;

import com.porto.bff.conta.timeline.bffcontastimeline.application.service.conta.GerenciarContaIaasService;
import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@ResponseBody
@RequiredArgsConstructor
public class GerenciamentoContasController implements GerenciamentoContasControllerOperations{

    private final GerenciarContaIaasService gerenciarContaIaasService;


    @Override
    public ResponseEntity<DadosResponseDto<ContaResponseDto>> buscarConta(
            String tokenCognito,
            String xItauAuth,
            String contaId) {

        var respostaService = gerenciarContaIaasService.getConta(
                 xItauAuth,
                 contaId
        );
        return ResponseEntity.status(HttpStatus.OK).body(respostaService);
    }

    @Override
    public ResponseEntity<DadosResponseDto<ContaSaldoResponseDto>> saldoConta(String tokenCognito, String xItauAuth, String contaId) {
        var respostaService = gerenciarContaIaasService.getContaSaldo(
                xItauAuth,
                contaId
        );
        return ResponseEntity.status(HttpStatus.OK).body(respostaService);
    }


    @Override
    public ResponseEntity<DadosResponseDto<ContaSumarioResponseDto>> sumarioConta(
            String tokenCognito,
            String xItauAuth,
            String contaId) {

        var responseService = gerenciarContaIaasService.contaSumario(
                tokenCognito,
                xItauAuth,
                contaId
        );

        return ResponseEntity.status(HttpStatus.OK).body(responseService);
    }

    @Override
    public ResponseEntity<Void> ediatarStatusConta(String tokenCognito, String xItauAuth, String contaId, ContaRequestDto requestDto) {
        gerenciarContaIaasService.editarStatusConta(
                xItauAuth,
                contaId,
                requestDto
        );

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @Override
    public ResponseEntity<Void> apagarConta(String tokenCognito, String xItauAuth, String contaId, RequestDeleteDto request) {
        gerenciarContaIaasService.apagarConta(
                xItauAuth,
                contaId,
                request);


        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }





}
