package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v2;

import org.junit.jupiter.api.Disabled;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@SpringBootTest
@AutoConfigureMockMvc
@Disabled
class AccountManagementControllerTest {
/*
    @Autowired
    AccountManagementController controller;

    @MockBean
    AccountManagementService service;

    @Autowired
    AccountManagementMapper mapper;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAccountData() {
        String cognitoToken = "dummyCognitoToken";
        String xItauAuth = "dummyXItauAuth";
        String accountId = "dummyAccountId";

        BankAccountDto bankAccountDto = new BankAccountDto(
                "Banco XYZ", "001", "1234", "567890", "0"
        );

        BankAccount bankAccount = new BankAccount(
                "Company XYZ", "001", "1234", "567890", "0", "Checking"
        );
        var backendResponseData = new BackendResponseData<>(new AccountDataEntityResponse(
                "123", bankAccount, "Active", "Checking", "01/01/2020", "01/01/2021"
        ));

        AccountDataDtoResponse dtoResponse = new AccountDataDtoResponse(
                "123", bankAccountDto, "Active", "Checking", "01/01/2020", "01/01/2021"
        );

        ApiResponseData<AccountDataDtoResponse> apiResponseData = new ApiResponseData<>(dtoResponse);

        when(service.getAccountData(anyString(), anyString())).thenReturn(backendResponseData);

        ResponseEntity<ApiResponseData<AccountDataDtoResponse>> response = controller.getAccountData(cognitoToken, xItauAuth, accountId);

        Assertions.assertNotNull(response);
    }


    @Test
    void getBalanceAccount() {
        var backendResponseData = new BackendResponseData<>(new AccountBalanceEntityResponse(0.0, 0.0, 0.0));
        when(this.service.getBalanceAccount(anyString(), anyString())).thenReturn(backendResponseData);
        var response = this.controller.getBalanceAccount("token", "token", "accountId");
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getSummaryAccount400() throws Exception {

        when(this.service.getSummaryAccount(anyString(), anyString(),anyString())).thenThrow(new BusinessException(400, "SUMARIO_ERROR", "Erro ao Integrar com a Portobank"));

        this.mockMvc.perform(get("/v2/conta-digital/gerenciar/sumario")
                        .header(AUTHORIZATION, "token")
                        .header("x-itau-auth", "token")
                        .header("x-account-id", "accountId")
        )
        .andExpect(status().is(400));
    }

    @Test
    void getBalanceErro400() throws Exception {

        when(this.service.getBalanceAccount(anyString(), anyString())).thenThrow(new BusinessException(400, "SUMARIO_ERROR", "Erro ao Integrar com a Portobank"));

        this.mockMvc.perform(get("/v2/conta-digital/gerenciar/saldo")
                        .header(AUTHORIZATION, "token")
                        .header("x-itau-auth", "token")
                        .header("x-account-id", "accountId")
                )
                .andExpect(status().is(400));
    }

    @Test
    void getAccountData400() throws Exception {

        when(this.service.getAccountData(anyString(),anyString())).thenThrow(new BusinessException(400, "SUMARIO_ERROR", "Erro ao Integrar com a Portobank"));

        this.mockMvc.perform(get("/v2/conta-digital/gerenciar/dados-conta")
                        .header(AUTHORIZATION, "token")
                        .header("x-itau-auth", "token")
                        .header("x-account-id", "accountId")
                )
                .andExpect(status().is(400));
    }


    @Test
    void getBalanceAccountError() throws Exception {
        when(this.service.getBalanceAccount(anyString(), anyString())).thenThrow(new FeignClientException("ERROR", "498", Collections.emptyList()));
        this.mockMvc.perform(get("/v2/conta-digital/gerenciar/saldo")
                        .header(AUTHORIZATION, "token")
                        .header("x-itau-auth", "token")
                        .header("x-account-id", "accountId")
                )
                .andExpect(status().is(498))
                .andExpect(content().json("{\"erros\":[]}"));
    }


    @Test
    void testResponseBody() throws JsonProcessingException {
        var dto = this.mapper.toAccountBalanceDto(new BackendResponseData<>(new AccountBalanceEntityResponse(10.5, 12.46, 34.76)));
        var responseJson = this.objectMapper.writeValueAsString(dto);
        var expected = "{\"dados\":{\"disponivel\":\"R$ 10,50\",\"reservada\":\"R$ 12,46\",\"bloqueado\":\"R$ 34,76\"}}";
        assertEquals(expected, responseJson);
    }

    @Test
    void testGetSummaryAccount() {
        String cognitoToken = "testToken";
        String xItauAuth = "testAuth";
        String accountId = "testId";
        var bankAccount = new BankAccount("", "", "", "", "", "");
        var account = new AccountDataEntityResponse("", bankAccount, "", "", "", "");
        var balance = new AccountBalanceEntityResponse(10.0, 11.0, 0.0);
        var summary = new AccountSummaryEntityResponse("", account, balance, true, "");
        var summaryData = new BackendResponseData<>(summary);
        when(service.getSummaryAccount(cognitoToken, xItauAuth, accountId)).thenReturn(summaryData);
        var response = this.controller.getSummaryAccount(cognitoToken, xItauAuth, accountId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
*/
}