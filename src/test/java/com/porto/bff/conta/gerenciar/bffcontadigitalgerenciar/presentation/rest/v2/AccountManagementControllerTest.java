package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.service.account.v2.AccountManagementService;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.BackendResponseData;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.account.balance.v2.AccountBalanceEntityResponse;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v2.mapper.AccountManagementMapper;
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

}