package com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.controller;

import com.porto.bff.conta.timeline.bffcontastimeline.application.service.conta.GerenciarContaIaasService;
import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto.ContaResponseDto;
import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto.ContaSaldoResponseDto;
import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto.ContaSumarioResponseDto;
import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto.DadosResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@ResponseBody
@RequiredArgsConstructor
public class GerenciamentoContasController implements GerenciamentoContasControllerOperations {

    private final GerenciarContaIaasService gerenciarContaIaasService;


    @Override
    public ResponseEntity<DadosResponseDto<ContaResponseDto>> dadosConta(String tokenCognito, String xItauAuth,
                                                                         String contaId) {
        var respostaService = this.gerenciarContaIaasService.getConta(xItauAuth, contaId);
        return ResponseEntity.status(HttpStatus.OK).body(respostaService);
    }

    @Override
    public ResponseEntity<DadosResponseDto<ContaSaldoResponseDto>> saldoConta(String tokenCognito, String xItauAuth,
                                                                              String contaId) {
        var respostaService = this.gerenciarContaIaasService.getContaSaldo(xItauAuth, contaId);
        return ResponseEntity.status(HttpStatus.OK).body(respostaService);
    }


    @Override
    public ResponseEntity<DadosResponseDto<ContaSumarioResponseDto>> sumarioConta(String tokenCognito,
                                                                                  String xItauAuth,
                                                                                  String contaId) {
        var responseService = gerenciarContaIaasService.contaSumario(tokenCognito, xItauAuth, contaId);
        return ResponseEntity.status(HttpStatus.OK).body(responseService);
    }

}
