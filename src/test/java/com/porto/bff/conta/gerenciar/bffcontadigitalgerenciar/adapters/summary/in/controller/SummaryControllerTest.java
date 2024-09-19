package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.summary.in.controller;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.balance.v2.in.controller.BalanceController;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.summary.v2.in.controller.SummaryController;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.account.AccountDataEntityResponse;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.account.AccountDataEntityResponseDomain;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.account.BankAccount;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.account.BankAccountDomain;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.backend.BackendResponseDataDomain;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.summary.SummaryEntityResponseDomain;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.ports.account.in.AccountDataInputPort;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.balance.BalanceEntityResponseDomain;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SummaryControllerTest {

    @MockBean
    AccountDataInputPort inputPort;

    @Autowired
    SummaryController controller;

    @Test
    void testGetSummaryAccount() {
        String cognitoToken = "testToken";
        String xItauAuth = "testAuth";
        String accountId = "testId";
        var bankAccount = new BankAccountDomain("", "", "", "", "", "");
        var account = new AccountDataEntityResponseDomain("", bankAccount, "", "", "", "");
        var balance = new BalanceEntityResponseDomain(10.0, 11.0, 0.0);
        var summary = new SummaryEntityResponseDomain("", account, balance, true, "");
        var summaryData = new BackendResponseDataDomain<>(summary);
        when(inputPort.getSummaryAccount(cognitoToken, xItauAuth, accountId)).thenReturn(summaryData.data());
        var response = this.controller.getSummaryAccount(cognitoToken, xItauAuth, accountId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
