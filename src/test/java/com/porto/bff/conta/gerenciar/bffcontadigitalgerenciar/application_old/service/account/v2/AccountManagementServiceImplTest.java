package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application_old.service.account.v2;

import org.mockito.junit.jupiter.MockitoSettings;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@MockitoSettings
class AccountManagementServiceImplTest {
/*
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


    @Test
    void testGetAccountData() {
        var bankAccount = new BankAccount("", "", "", "", "", "");
        AccountDataEntityResponse dataResponse = new AccountDataEntityResponse("",bankAccount,"","","","");
        BackendResponseData<AccountDataEntityResponse> backendResponse = new BackendResponseData<>(dataResponse);


        when(adapter.getAccountData(anyString(), anyString())).thenReturn(backendResponse);

        String xItauAuth = "auth";
        String accountId = "12345";
        BackendResponseData<AccountDataEntityResponse> result = service.getAccountData(xItauAuth, accountId);

        verify(headerValidation).isValidHeaderProjet(xItauAuth, accountId);
        verify(adapter).getAccountData(xItauAuth, accountId);
        assertEquals(dataResponse, result.data());
    }
*/
}