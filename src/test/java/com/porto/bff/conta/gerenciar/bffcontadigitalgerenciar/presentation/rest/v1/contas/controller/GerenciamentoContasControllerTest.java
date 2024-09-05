package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v1.contas.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application_old.mapper.GerenciarMapper;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application_old.service.conta.GerenciarContaIaasService;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.DataResponseIassPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.conta.AccountFlagsResponseIaasPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.conta.AccountResponseIaasPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.conta.BankAccountResponseIassPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.saldo.BalanceResponseIaasPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.sumario.SumarioResponseIaasPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v1.contas.dto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class GerenciamentoContasControllerTest {

    @Mock
    private GerenciarContaIaasService gerenciarContaIaasService;

    @Mock
    private GerenciarMapper gerenciarMapper;

    @InjectMocks
    private GerenciamentoContasController gerenciamentoContasController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testDadosConta() throws IOException {
        ResponseEntity<DadosResponseDto<ContaResponseDto>> responseEntity = gerenciamentoContasController.dadosConta("token", "xItauAuth", "contaId");

        verify(gerenciarContaIaasService, times(1)).getConta(anyString(), anyString());
        verify(gerenciarMapper, times(1)).paraDadosContaResponseDto(any());

    }

    @Test
    void testSaldoConta() {
        when(gerenciarContaIaasService.getContaSaldo(anyString(), anyString())).thenReturn(mock());
        when(gerenciarMapper.paraDadosSaldoResponseDto(any())).thenReturn(mock());

        ResponseEntity<DadosResponseDto<SaldoResponseDto>> responseEntity = gerenciamentoContasController.saldoConta("token", "xItauAuth", "contaId");

        verify(gerenciarContaIaasService, times(1)).getContaSaldo(anyString(), anyString());
        verify(gerenciarMapper, times(1)).paraDadosSaldoResponseDto(any());

    }

    @Test
    void testConvertePoliticasEmFlagsResponseIaasPorto() {
        List<String> politicas = List.of("politica1", "politica2");

        List<AccountFlagsResponseIaasPorto> result = gerenciamentoContasController.convertePoliticasEmFlagsResponseIaasPorto(politicas);

        assertEquals(politicas.size(), result.size());

        for (AccountFlagsResponseIaasPorto flag : result) {
            assertEquals(politicas.contains(flag.policyId()), true);
        }
    }

    @Test
    void testSumarioConta() throws IOException {
        String tokenCognito = "token";
        String xItauAuth = "xItauAuth";
        String contaId = "contaId";
        DataResponseIassPorto<SumarioResponseIaasPorto> mockSumarioResponse = new DataResponseIassPorto<>(getSumarioResponseIaasPorto());
        when(gerenciarContaIaasService.contaSumario(eq(tokenCognito), eq(xItauAuth), eq(contaId))).thenReturn(mockSumarioResponse);
        ResponseEntity<DadosResponseDto<SumarioResponseDto>> responseEntity = gerenciamentoContasController.sumarioConta(tokenCognito, xItauAuth, contaId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }

    @Test
    void testSumarioContaValidMockBloqueioDeContaEstaAtivo() throws IOException {
        ReflectionTestUtils.setField(gerenciamentoContasController, "mockBloqueioDeContaEstaAtivo", true);
        ReflectionTestUtils.setField(gerenciamentoContasController, "mockBloqueioContas", "{\"dados\": [{\"contaId\": \"someId\", \"politicas\": [\"policy1\", \"policy2\"]}]}");


        String tokenCognito = "tokenCognito";
        String xItauAuth = "xItauAuth";
        String contaId = "contaId";
        SumarioResponseIaasPorto sumarioResponseIaasPorto = getSumarioResponseIaasPorto();
        when(gerenciarContaIaasService.contaSumario(tokenCognito, xItauAuth, contaId))
                .thenReturn(new DataResponseIassPorto<>(sumarioResponseIaasPorto));


        DadosResponseDto<SumarioResponseDto> expectedResponseDto = new DadosResponseDto<>(getSumarioResponseDto());
        when(gerenciarMapper.paraDadosSumarioResponseDto(any()))
                .thenReturn(expectedResponseDto);


        ResponseEntity<DadosResponseDto<SumarioResponseDto>> result = gerenciamentoContasController.sumarioConta(tokenCognito, xItauAuth, contaId);


        verify(gerenciarContaIaasService, times(1)).contaSumario(tokenCognito, xItauAuth, contaId);
        verify(gerenciarMapper, times(1)).paraDadosSumarioResponseDto(any());
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(expectedResponseDto, result.getBody());

    }

    @Test
    void testSumarioContaWithMockBloqueioAtivo() throws IOException {
        SumarioResponseIaasPorto sumarioResponseIaasPortoMock = getSumarioResponseIaasPorto();
        DataResponseIassPorto<SumarioResponseIaasPorto> sumarioResponseIaasPortoDataResponseIassPortoMock = new DataResponseIassPorto<>(sumarioResponseIaasPortoMock);

        when(gerenciarContaIaasService.contaSumario(anyString(), anyString(), anyString())).thenReturn(sumarioResponseIaasPortoDataResponseIassPortoMock);

        Flags flagsMock = getFlags();

        List<String> politicasPorContaIdMock = List.of("teste");

        ResponseEntity<DadosResponseDto<SumarioResponseDto>> responseEntity = gerenciamentoContasController.sumarioConta("token", "xItauAuth", "contaId");

        assertNotNull(responseEntity);

    }

    @Test
    void testMapeiaMockBloqueioConta() throws IOException {
        ObjectMapper objectMapperMock = mock(ObjectMapper.class);

        String validJsonString = "{\"dados\": [{\"contaId\": \"someId\", \"politicas\": [\"policy1\", \"policy2\"]}]}";

        when(objectMapperMock.readValue(eq(validJsonString), eq(Flags.class))).thenReturn(mock());

        GerenciamentoContasController gerenciamentoContasController =
                new GerenciamentoContasController(gerenciarContaIaasService, gerenciarMapper);

        Flags result = gerenciamentoContasController.mapeiaMockBloqueioConta(validJsonString);

        assertNotNull(result);
    }

    @Test
    void testConvertePoliticasEmFlagsResponseIaasPorto2() {
        GerenciamentoContasController controller = new GerenciamentoContasController(gerenciarContaIaasService, gerenciarMapper);

        List<String> samplePoliticas = List.of("policy1", "policy2");

        List<AccountFlagsResponseIaasPorto> result = controller.convertePoliticasEmFlagsResponseIaasPorto(samplePoliticas);

        assertEquals(samplePoliticas.size(), result.size());

    }

    private Flags getFlags() {
        Flags flags = new Flags();


        flags.setDados(Arrays.asList(
                Dados.builder().contaId("123").politicas(Arrays.asList("politica1", "politica2")).build(),
                Dados.builder().contaId("456").politicas(Arrays.asList("politica3", "politica4")).build()
        ));

        return flags;
    }

    private AccountResponseIaasPorto getAccountResponseIaasPorto() {
        AccountResponseIaasPorto accountResponse = new AccountResponseIaasPorto(
                "idValue",
                getBankAccountResponseIassPorto(),
                "statusValue",
                "typeValue",
                Collections.singletonList(getAccountFlagsResponseIaasPorto()),
                "createdAtValue",
                "updatedAtValue"
        );

        return accountResponse;

    }

    private BankAccountResponseIassPorto getBankAccountResponseIassPorto() {
        BankAccountResponseIassPorto bankAccount = new BankAccountResponseIassPorto(
                "bankValue",
                "branchValue",
                "numberValue",
                "checkDigitValue"
        );
        return bankAccount;
    }

    private AccountFlagsResponseIaasPorto getAccountFlagsResponseIaasPorto() {
        AccountFlagsResponseIaasPorto accountFlagsResponse = new AccountFlagsResponseIaasPorto(
                "idValue",
                "policyIdValue",
                "timestampValue"
        );
        return accountFlagsResponse;
    }

    private SumarioResponseIaasPorto getSumarioResponseIaasPorto() {

        SumarioResponseIaasPorto sumarioResponse = new SumarioResponseIaasPorto(
                "documentValue",
                new DataResponseIassPorto<>(getAccountResponseIaasPorto()),
                new DataResponseIassPorto<>(getBalanceResponseIaasPorto()),
                true,
                "quantidadeChavePixValue"
        );

        return sumarioResponse;

    }

    private BalanceResponseIaasPorto getBalanceResponseIaasPorto() {
        BalanceResponseIaasPorto balanceResponse = new BalanceResponseIaasPorto(
                "accountIdValue",
                100.0,
                20.0,
                10.0
        );
        return balanceResponse;
    }

    private SumarioResponseDto getSumarioResponseDto() {
        SumarioResponseDto sumarioResponseDto = new SumarioResponseDto(
                "nomeBancoValue",
                "codigoBancoValue",
                "agenciaValue",
                "numeroContaValue",
                "digitoContaValue",
                "saldoValue",
                "statusContaValue",
                getBloqueiosContaDto(),
                "documentoValue",
                true,
                "quantidadeChavePixValue"
        );

        return sumarioResponseDto;
    }

    private BloqueiosContaDto getBloqueiosContaDto() {
        BloqueiosContaDto bloqueiosContaDto = new BloqueiosContaDto(
                List.of("politica1", "politica2"), // Replace with your actual list of policies
                true,
                true,
                "tituloBloqueioBoletoValue",
                "tituloBloqueioPixValue",
                "subTituloBloqueioValue"
        );
        return bloqueiosContaDto;
    }
}