package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v1.contas.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class GerenciamentoContasControllerTest {
/*
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
        ResponseEntity<DataResponseDto<AccountResponseDto>> responseEntity = gerenciamentoContasController.dadosConta("token", "xItauAuth", "contaId");

        verify(gerenciarContaIaasService, times(1)).getAccount(anyString(), anyString());
        verify(gerenciarMapper, times(1)).paraDadosContaResponseDto(any());

    }

    @Test
    void testSaldoConta() {
        when(gerenciarContaIaasService.getContaSaldo(anyString(), anyString())).thenReturn(mock());
        when(gerenciarMapper.paraDadosSaldoResponseDto(any())).thenReturn(mock());

        ResponseEntity<DataResponseDto<BalanceResponseDto>> responseEntity = gerenciamentoContasController.saldoConta("token", "xItauAuth", "contaId");

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
        DataResponseIassPorto<SummaryResponseIaasPorto> mockSumarioResponse = new DataResponseIassPorto<>(getSumarioResponseIaasPorto());
        when(gerenciarContaIaasService.contaSumario(eq(tokenCognito), eq(xItauAuth), eq(contaId))).thenReturn(mockSumarioResponse);
        ResponseEntity<DataResponseDto<SummaryResponseDto>> responseEntity = gerenciamentoContasController.sumarioConta(tokenCognito, xItauAuth, contaId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }

    @Test
    void testSumarioContaValidMockBloqueioDeContaEstaAtivo() throws IOException {
        ReflectionTestUtils.setField(gerenciamentoContasController, "mockBloqueioDeContaEstaAtivo", true);
        ReflectionTestUtils.setField(gerenciamentoContasController, "mockBloqueioContas", "{\"dados\": [{\"contaId\": \"someId\", \"politicas\": [\"policy1\", \"policy2\"]}]}");


        String tokenCognito = "tokenCognito";
        String xItauAuth = "xItauAuth";
        String contaId = "contaId";
        SummaryResponseIaasPorto sumarioResponseIaasPorto = getSumarioResponseIaasPorto();
        when(gerenciarContaIaasService.contaSumario(tokenCognito, xItauAuth, contaId))
                .thenReturn(new DataResponseIassPorto<>(sumarioResponseIaasPorto));


        DataResponseDto<SummaryResponseDto> expectedResponseDto = new DataResponseDto<>(getSumarioResponseDto());
        when(gerenciarMapper.paraDadosSumarioResponseDto(any()))
                .thenReturn(expectedResponseDto);


        ResponseEntity<DataResponseDto<SummaryResponseDto>> result = gerenciamentoContasController.sumarioConta(tokenCognito, xItauAuth, contaId);


        verify(gerenciarContaIaasService, times(1)).contaSumario(tokenCognito, xItauAuth, contaId);
        verify(gerenciarMapper, times(1)).paraDadosSumarioResponseDto(any());
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(expectedResponseDto, result.getBody());

    }

    @Test
    void testSumarioContaWithMockBloqueioAtivo() throws IOException {
        SummaryResponseIaasPorto sumarioResponseIaasPortoMock = getSumarioResponseIaasPorto();
        DataResponseIassPorto<SummaryResponseIaasPorto> sumarioResponseIaasPortoDataResponseIassPortoMock = new DataResponseIassPorto<>(sumarioResponseIaasPortoMock);

        when(gerenciarContaIaasService.contaSumario(anyString(), anyString(), anyString())).thenReturn(sumarioResponseIaasPortoDataResponseIassPortoMock);

        Flags flagsMock = getFlags();

        List<String> politicasPorContaIdMock = List.of("teste");

        ResponseEntity<DataResponseDto<SummaryResponseDto>> responseEntity = gerenciamentoContasController.sumarioConta("token", "xItauAuth", "contaId");

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
                AccountData.builder().contaId("123").politicas(Arrays.asList("politica1", "politica2")).build(),
                AccountData.builder().contaId("456").politicas(Arrays.asList("politica3", "politica4")).build()
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

    private SummaryResponseIaasPorto getSumarioResponseIaasPorto() {

        SummaryResponseIaasPorto sumarioResponse = new SummaryResponseIaasPorto(
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

    private SummaryResponseDto getSumarioResponseDto() {
        SummaryResponseDto sumarioResponseDto = new SummaryResponseDto(
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

    private AccountBlockingDto getBloqueiosContaDto() {
        AccountBlockingDto bloqueiosContaDto = new AccountBlockingDto(
                List.of("politica1", "politica2"), // Replace with your actual list of policies
                true,
                true,
                "tituloBloqueioBoletoValue",
                "tituloBloqueioPixValue",
                "subTituloBloqueioValue"
        );
        return bloqueiosContaDto;
    }*/
}