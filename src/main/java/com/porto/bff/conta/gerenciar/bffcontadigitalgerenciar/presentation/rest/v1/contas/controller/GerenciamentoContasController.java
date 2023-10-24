package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v1.contas.controller;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.mapper.GerenciarMapper;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.service.conta.GerenciarContaIaasService;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v1.contas.dto.ContaResponseDto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v1.contas.dto.DadosResponseDto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v1.contas.dto.SaldoResponseDto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v1.contas.dto.SumarioResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@ResponseBody
@RequiredArgsConstructor
public class GerenciamentoContasController implements GerenciamentoContasControllerOperations {

    private final GerenciarContaIaasService service;
    private final GerenciarMapper mapper;


    @Override
    public ResponseEntity<DadosResponseDto<ContaResponseDto>> dadosConta(String tokenCognito, String xItauAuth,
                                                                         String contaId) {
        var respostaService = this.service.getConta(xItauAuth, contaId);
        return ResponseEntity.status(HttpStatus.OK).body(this.mapper.paraDadosContaResponseDto(respostaService));
    }

    @Override
    public ResponseEntity<DadosResponseDto<SaldoResponseDto>> saldoConta(String tokenCognito, String xItauAuth,
                                                                         String contaId) {
        var respostaService = this.service.getContaSaldo(xItauAuth, contaId);
        return ResponseEntity.status(HttpStatus.OK).body(this.mapper.paraDadosSaldoResponseDto(respostaService));
    }


    @Override
    public ResponseEntity<DadosResponseDto<SumarioResponseDto>> sumarioConta(String tokenCognito, String xItauAuth,
                                                                             String contaId) {
        var responseService = service.contaSumario(tokenCognito, xItauAuth, contaId);

        DadosResponseDto<SumarioResponseDto> sumarioResponseDtoDadosResponseDto =
                this.mapper.paraDadosSumarioResponseDto(responseService);

        return ResponseEntity.status(HttpStatus.OK)
                .body(sumarioResponseDtoDadosResponseDto);
    }

}
