package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.balance.v2.in.controller;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.backend.BackendResponseDataDomain;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.balance.BalanceEntityResponseDomain;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.ports.account.in.AccountDataInputPort;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.ports.balance.in.BalanceInputPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BalanceControllerTest {

    @Autowired
    BalanceController controller;

    @MockBean
    AccountDataInputPort inputPort;



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
