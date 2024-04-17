package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.account.v2;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.BackendResponseData;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.account.balance.v2.AccountBalanceEntityResponse;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.account.data.v2.AccountDataEntityResponse;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.account.data.v2.BankAccount;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.account.sumary.v2.AccountSummaryEntityResponse;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.cartoes.ListaCartoesResponse;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.account.v2.client.AccountManagementClient;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.conta.client.CartoesPortoClient;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.decodertoken.DecodificarAccessToken;
import com.porto.experiencia.cliente.conta.digital.commons.domain.exception.FeignClientException;
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

    @Mock
    CartoesPortoClient cardPortoClient;

    @Mock
    DecodificarAccessToken tokenDecoder;

    @Test
    void getBalanceAccount() {
        double balanceValue = 0.0;
        var response = new AccountBalanceEntityResponse(balanceValue, balanceValue, balanceValue);
        var backendResponseData = new BackendResponseData<>(response);
        when(this.client.getBalanceAccount(anyString(), anyString(), anyString(), anyString())).thenReturn(response);
        var responseData = assertDoesNotThrow(() ->
                this.adapter.getBalanceAccount("token", "accountID"));
        assertEquals(responseData, backendResponseData);
    }

    @Test
    void testGetSummaryAccount() {
        String cognitoToken = "cognitoToken";
        String xItauAuth = "xItauAuth";
        String accountId = "accountId";

        var bankAccount = new BankAccount("", "", "", "", "", "");
        var account = new AccountDataEntityResponse("", bankAccount, "", "", "", "");
        var balance = new AccountBalanceEntityResponse(10.0, 11.0, 0.0);

        when(tokenDecoder.getCpfPorToken(cognitoToken)).thenReturn("document");
        when(client.getBalanceAccount(anyString(), anyString(), anyString(), anyString())).thenReturn((balance));
        when(client.getAccountData(anyString(), anyString(), anyString(), anyString(), anyString())).thenReturn((account));
        when(cardPortoClient.getCardsByuser(cognitoToken)).thenReturn(new ListaCartoesResponse());

        BackendResponseData<AccountSummaryEntityResponse> result = this.adapter.getSummaryAccount(cognitoToken, xItauAuth, accountId);

        assertNotNull(result);
        assertEquals("document", result.data().document());
        assertEquals(10.0, result.data().balance().available());
    }

    @Test
    void testGetSummaryAccount2() {
        String cognitoToken = "cognitoToken";
        String xItauAuth = "xItauAuth";
        String accountId = "accountId";
        var bankAccount = new BankAccount("", "", "", "", "", "");
        var account = new AccountDataEntityResponse("", bankAccount, "", "", "", "");
        var balance = new AccountBalanceEntityResponse(10.0, 11.0, 0.0);
        when(tokenDecoder.getCpfPorToken(cognitoToken)).thenReturn("document");
        when(client.getBalanceAccount(anyString(), anyString(), anyString(), anyString())).thenReturn((balance));
        when(client.getAccountData(anyString(), anyString(), anyString(), anyString(), anyString())).thenReturn((account));
        when(cardPortoClient.getCardsByuser(cognitoToken)).thenThrow(FeignClientException.class);
        assertDoesNotThrow(() -> this.adapter.getSummaryAccount(cognitoToken, xItauAuth, accountId));
    }
}