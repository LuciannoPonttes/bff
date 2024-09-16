package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.balance.v2.in.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.balance.v2.in.controller.mapper.BalanceMapper;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.backend.BackendResponseDataDomain;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.balance.BalanceEntityResponseDomain;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.ports.balance.in.BalanceInputPort;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application_old.service.account.v2.AccountManagementService;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.BackendResponseData;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.account.balance.v2.AccountBalanceEntityResponse;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v2.AccountManagementController;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v2.mapper.AccountManagementMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@Disabled
public class BalanceControllerTest {

    @Autowired
    BalanceController controller;

    @MockBean
    BalanceInputPort inputPort;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getBalanceAccount() {
        var backendResponseData = new BackendResponseDataDomain<>(new BalanceEntityResponseDomain(0.0, 0.0, 0.0));
        when(this.inputPort.getBalanceAccount(anyString(), anyString())).thenReturn(backendResponseData);
        var response = this.controller.getBalanceAccount("token", "token", "accountId");
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
