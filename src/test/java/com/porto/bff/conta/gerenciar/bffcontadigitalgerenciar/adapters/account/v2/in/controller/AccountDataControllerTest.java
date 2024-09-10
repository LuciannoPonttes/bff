package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.account.v2.in.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.account.v2.in.controller.response.dto.AccountDataResponseDto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.account.AccountDataEntityResponseDomain;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.account.BankAccountDomain;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.backend.BackendResponseDataDomain;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.ports.account.in.AccountDataInputPort;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application_old.service.account.v2.AccountManagementService;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.BackendResponseData;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.account.balance.v2.AccountBalanceEntityResponse;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.account.data.v2.AccountDataEntityResponse;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.account.data.v2.BankAccount;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.account.sumary.v2.AccountSummaryEntityResponse;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v2.dto.AccountDataDtoResponse;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v2.dto.BankAccountDto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v2.mapper.AccountManagementMapper;
import com.porto.experiencia.cliente.conta.digital.commons.domain.exception.BusinessException;
import com.porto.experiencia.cliente.conta.digital.commons.domain.exception.FeignClientException;
import com.porto.experiencia.cliente.conta.digital.commons.web.model.ApiResponseData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc

public class AccountDataControllerTest {

    @Autowired
    AccountDataController controller;

    @MockBean
    AccountDataInputPort service;

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

        BankAccountDomain bankAccount = new BankAccountDomain(
                "Company XYZ", "001", "1234", "567890", "0", "Checking"
        );
        var backendResponseData = new BackendResponseDataDomain<>(new AccountDataEntityResponseDomain(
                "123", bankAccount, "Active", "Checking", "01/01/2020", "01/01/2021"
        ));

        AccountDataDtoResponse dtoResponse = new AccountDataDtoResponse(
                "123", bankAccountDto, "Active", "Checking", "01/01/2020", "01/01/2021"
        );

        ApiResponseData<AccountDataDtoResponse> apiResponseData = new ApiResponseData<>(dtoResponse);

        when(service.getAccountData(anyString(), anyString())).thenReturn(backendResponseData);

        ResponseEntity<ApiResponseData<AccountDataResponseDto>> response = controller.getAccountData(cognitoToken, xItauAuth, accountId);

        Assertions.assertNotNull(response);
    }



/*

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
*/

}
