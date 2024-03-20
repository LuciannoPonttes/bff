package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.account.v2;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.BackendResponseData;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.account.balance.v2.AccountBalanceEntityResponse;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.account.v2.client.AccountManagementClient;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@MockitoSettings
class AccountManagementAdapterImplTest {

    @InjectMocks
    AccountManagementAdapterImpl adapter;
    @Mock
    AccountManagementClient client;

    @Test
    void getBalanceAccount() {
        double balanceValue = 0.0;
        var backendResponseData = new BackendResponseData<>(new AccountBalanceEntityResponse(balanceValue, balanceValue, balanceValue));
        when(this.client.getBalanceAccount(anyString(), anyString(), anyString(), anyString())).thenReturn(backendResponseData);
        var responseData = assertDoesNotThrow(() ->
                this.adapter.getBalanceAccount("token", "accountID"));
        assertEquals(responseData, backendResponseData);
    }
}