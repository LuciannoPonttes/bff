package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.service.account.v2.AccountManagementService;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.BackendResponseData;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.account.balance.v2.AccountBalanceEntityResponse;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.account.data.v2.AccountDataEntityResponse;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.account.data.v2.BankAccount;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.account.sumary.v2.AccountSummaryEntityResponse;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.account.v2.AccountManagementAdapterImpl;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.account.v2.client.AccountManagementClient;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v2.mapper.AccountManagementMapper;
import com.porto.experiencia.cliente.conta.digital.commons.domain.exception.BusinessException;
import com.porto.experiencia.cliente.conta.digital.commons.domain.exception.FeignClientException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
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
class AccountManagementControllerTest {

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

}