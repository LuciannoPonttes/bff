package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.account.v2;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.service.account.v2.AccountManagementServiceImpl;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.BackendResponseData;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.account.balance.v2.AccountBalanceEntityResponse;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.account.data.v2.AccountDataEntityResponse;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.account.data.v2.BankAccount;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.account.sumary.v2.AccountSummaryEntityResponse;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.cartoes.ClienteDomain;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.cartoes.ListarCartoesResponseBodyDomain;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.cartoes.PortoCardResponse;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.cartoes.PortoCardResponseData;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.account.v2.client.AccountManagementClient;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.conta.client.PortoCardClient;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.decodertoken.DecodificarAccessToken;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.pix.v2.client.PixManagementV2Client;
import com.porto.experiencia.cliente.conta.digital.commons.domain.exception.FeignClientException;
import com.porto.experiencia.cliente.conta.digital.commons.web.model.ApiResponseData;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.Collections;

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
    PortoCardClient cardPortoClient;

    @Mock
    PixManagementV2Client pixKeysClient;

    @Mock
    DecodificarAccessToken tokenDecoder;

    @InjectMocks
    private AccountManagementServiceImpl accountManagementService;

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
        when(this.pixKeysClient.getPixKeyFromAnAccount(anyString(), anyString(), anyString())).thenReturn(new ApiResponseData<>(Collections.emptyList()));
        when(cardPortoClient.getCardsByuser(cognitoToken)).thenReturn(new PortoCardResponse());

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
        var balance = new AccountBalanceEntityResponse(10.0, 11.0, 0.0);
        when(tokenDecoder.getCpfPorToken(cognitoToken)).thenReturn("document");
        when(client.getBalanceAccount(anyString(), anyString(), anyString(), anyString())).thenReturn((balance));
        when(client.getAccountData(anyString(), anyString(), anyString(), anyString(), anyString())).thenThrow(FeignClientException.class);
        when(cardPortoClient.getCardsByuser(cognitoToken)).thenThrow(FeignClientException.class);
        assertThrows(FeignClientException.class, () -> this.adapter.getSummaryAccount(cognitoToken, xItauAuth, accountId));
    }

    @Test
    void testGetAccountData() {

        String xItauAuth = "Bearer token";
        String accountId = "12345";
        AccountDataEntityResponse expectedResponse = new AccountDataEntityResponse(
                "12345",
                new BankAccount("", "", "", "", "", ""),
                "active",
                "type",
                "2024-08-06T12:34:56",
                "2024-08-06T12:34:56"
        );


        when(client.getAccountData(anyString(), anyString(), anyString(), anyString(), anyString()))
                .thenReturn(expectedResponse);

        BackendResponseData<AccountDataEntityResponse> actualResponse = adapter.getAccountData(xItauAuth, accountId);

        assertEquals(expectedResponse, actualResponse.data());
    }

    @Test
    void testGetSummaryAccountWithNonEmptyPortoCardList() {
        String cognitoToken = "cognitoToken";
        String xItauAuth = "xItauAuth";
        String accountId = "accountId";

        var card = new ListarCartoesResponseBodyDomain(
                "active", "Visa", "Standard Card", "1234", "personal", "2024-08-06", "credit", "visa-logo", "black", "none"
        );
        var portoCardResponseData = new PortoCardResponseData(
                "productCode",
                new ClienteDomain(), // Mock ClienteDomain if needed
                Collections.singletonList(card)
        );
        var portoCardResponse = new PortoCardResponse();
        portoCardResponse.setDados(portoCardResponseData);

        var bankAccount = new BankAccount("", "", "", "", "", "");
        var account = new AccountDataEntityResponse("", bankAccount, "", "", "", "");
        var balance = new AccountBalanceEntityResponse(10.0, 11.0, 0.0);
        var pixKeyResponse = new ApiResponseData<>(Collections.emptyList());

        when(tokenDecoder.getCpfPorToken(cognitoToken)).thenReturn("document");
        when(client.getBalanceAccount(anyString(), anyString(), anyString(), anyString())).thenReturn(balance);
        when(client.getAccountData(anyString(), anyString(), anyString(), anyString(), anyString())).thenReturn(account);
        when(pixKeysClient.getPixKeyFromAnAccount(anyString(), anyString(), anyString())).thenReturn(pixKeyResponse);
        when(cardPortoClient.getCardsByuser(cognitoToken)).thenReturn(portoCardResponse);

        BackendResponseData<AccountSummaryEntityResponse> result = this.adapter.getSummaryAccount(cognitoToken, xItauAuth, accountId);

        assertNotNull(result);
        assertEquals("document", result.data().document());
        assertEquals(10.0, result.data().balance().available());
        assertTrue(result.data().hasPortoCard());
    }
}