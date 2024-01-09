package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v1.contas.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.mapper.GerenciarMapper;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.service.conta.GerenciarContaIaasService;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.DataResponseIassPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.conta.AccountFlagsResponseIaasPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.conta.AccountResponseIaasPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.sumario.SumarioResponseIaasPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v1.contas.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;


@Controller
@ResponseBody
@RequiredArgsConstructor
public class GerenciamentoContasController implements GerenciamentoContasControllerOperations {

    private final GerenciarContaIaasService service;
    private final GerenciarMapper mapper;

    @Value("${blocking-mock.activated}")
    private boolean mockBloqueioDeContaEstaAtivo;

    @Value("${blocking-mock.account}")
    private String mockBloqueioContas;

    @Override
    public ResponseEntity<DadosResponseDto<ContaResponseDto>> dadosConta(String tokenCognito, String xItauAuth,
                                                                         String contaId) throws IOException {
        DataResponseIassPorto<AccountResponseIaasPorto> conta = this.service.getConta(xItauAuth, contaId);
        AccountResponseIaasPorto accountResponseIaasPorto = conta.data();

        if(mockBloqueioDeContaEstaAtivo) {
            Flags flags = mapeiaMockBloqueioConta(mockBloqueioContas);
            List<String> politicasPorContaId = flags.buscaPoliticasPorContaId(contaId);

            accountResponseIaasPorto = accountResponseIaasPorto.withFlags(convertePoliticasEmFlagsResponseIaasPorto(politicasPorContaId));
        }

        return ResponseEntity.status(HttpStatus.OK).body(this.mapper.paraDadosContaResponseDto(new DataResponseIassPorto<>(accountResponseIaasPorto)));
    }

    @Override
    public ResponseEntity<DadosResponseDto<SaldoResponseDto>> saldoConta(String tokenCognito, String xItauAuth,
                                                                         String contaId) {
        var respostaService = this.service.getContaSaldo(xItauAuth, contaId);
        return ResponseEntity.status(HttpStatus.OK).body(this.mapper.paraDadosSaldoResponseDto(respostaService));
    }


    @Override
    public ResponseEntity<DadosResponseDto<SumarioResponseDto>> sumarioConta(String tokenCognito, String xItauAuth,
                                                                             String contaId) throws IOException {
        DataResponseIassPorto<SumarioResponseIaasPorto> sumarioResponseIaasPortoDataResponseIassPorto = service.contaSumario(tokenCognito, xItauAuth, contaId);
        SumarioResponseIaasPorto sumarioResponseIaasPorto = sumarioResponseIaasPortoDataResponseIassPorto.data();

        if(mockBloqueioDeContaEstaAtivo) {
            Flags flags = mapeiaMockBloqueioConta(mockBloqueioContas);
            List<String> politicasPorContaId = flags.buscaPoliticasPorContaId(contaId);

            AccountResponseIaasPorto modifiedResponseService = sumarioResponseIaasPortoDataResponseIassPorto.data().account().data().withFlags(convertePoliticasEmFlagsResponseIaasPorto(politicasPorContaId));
            sumarioResponseIaasPorto = sumarioResponseIaasPortoDataResponseIassPorto.data().withAccount(new DataResponseIassPorto<>(modifiedResponseService));
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(this.mapper.paraDadosSumarioResponseDto(new DataResponseIassPorto<>(sumarioResponseIaasPorto)));
    }

    public static Flags mapeiaMockBloqueioConta(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, Flags.class);
    }

    public List<AccountFlagsResponseIaasPorto> convertePoliticasEmFlagsResponseIaasPorto(List<String> politicas) {
        List<AccountFlagsResponseIaasPorto> accountFlagsResponseIaasPortos = new ArrayList<>();
        for (String politica : politicas) {
            accountFlagsResponseIaasPortos.add(new AccountFlagsResponseIaasPorto(UUID.randomUUID().toString(), politica, LocalDateTime.now().toString()));
        }
        return accountFlagsResponseIaasPortos;
    }
}
