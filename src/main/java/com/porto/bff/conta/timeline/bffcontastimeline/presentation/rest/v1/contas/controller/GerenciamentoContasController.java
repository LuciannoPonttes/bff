package com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.controller;

import com.porto.bff.conta.timeline.bffcontastimeline.application.service.conta.GerenciarContaIaasService;
import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto.ContaEditadaResponseDto;
import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto.ContaRequestDto;
import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto.ContaResponseDto;
import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto.DadosResponseDto;
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
            String xAccountProvider,
            String xAccountId,
            String contaId,
            String campos) {

        var respostaService = gerenciarContaIaasService.getConta(
                 tokenCognito,
                 xAccountProvider,
                 xAccountId,
                 contaId,
                 campos
        );
        return ResponseEntity.status(HttpStatus.OK).body(respostaService);
    }

    @Override
    public ResponseEntity<ContaEditadaResponseDto> ediatarStatusConta(String tokenCognito, String xAccountProvider, String xAccountId, String contaId, ContaRequestDto requestDto) {
        return null;
    }

    @Override
    public ResponseEntity<ContaEditadaResponseDto> apagarConta(String tokenCognito, String xAccountProvider, String xAccountId, String contaId) {
        return null;
    }

    @Override
    public ResponseEntity<ContaEditadaResponseDto> consultarSaldoConta(String tokenCognito, String xAccountProvider, String xAccountId, String contaId) {
        return null;
    }
}
