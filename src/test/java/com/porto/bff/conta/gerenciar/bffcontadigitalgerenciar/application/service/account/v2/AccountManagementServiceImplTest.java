package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.service.account.v2;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.common.utils.HeaderValidation;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.BackendResponseData;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.account.balance.v2.AccountBalanceEntityResponse;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.account.data.v2.AccountDataEntityResponse;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.account.data.v2.BankAccount;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.account.sumary.v2.AccountSummaryEntityResponse;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.account.v2.AccountManagementAdapterImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@MockitoSettings
class AccountManagementServiceImplTest {

    @InjectMocks
    AccountManagementServiceImpl service;

    @Mock
    AccountManagementAdapterImpl adapter;
    @Mock
    HeaderValidation headerValidation;


    @Test
    void getBalanceAccount() {
        double balanceValue = 0.0;
        var backendResponseData = new BackendResponseData<>(new AccountBalanceEntityResponse(balanceValue, balanceValue, balanceValue));
        when(this.adapter.getBalanceAccount(anyString(), anyString())).thenReturn(backendResponseData);
        doNothing().when(headerValidation).isValidHeaderProjet(anyString(), anyString());
        var responseData = assertDoesNotThrow(() ->
                this.service.getBalanceAccount("token", "accountID"));
        assertEquals(responseData, backendResponseData);
    }

    @Test
    void testGetSummaryAccount() {
        String cognitoToken = "testCognitoToken";
        String xItauAuth = "testXItauAuth";
        String accountId = "testAccountId";
        var bankAccount = new BankAccount("","","","","","");
        var account = new AccountDataEntityResponse("", bankAccount, "","","","");
        var balance = new AccountBalanceEntityResponse(10.0, 11.0,0.0);
        var summary = new AccountSummaryEntityResponse("",account,balance,true,"");
        BackendResponseData<AccountSummaryEntityResponse> expectedResponse = new BackendResponseData<>(summary);
        when(adapter.getSummaryAccount(cognitoToken, xItauAuth, accountId)).thenReturn(expectedResponse);
        BackendResponseData<AccountSummaryEntityResponse> actualResponse = service.getSummaryAccount(cognitoToken, xItauAuth, accountId);
        verify(adapter).getSummaryAccount(cognitoToken, xItauAuth, accountId);
        assertEquals(expectedResponse, actualResponse);
    }
}