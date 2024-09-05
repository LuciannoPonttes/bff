package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.account.v2;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.account.v2.client.AccountManagementClient;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.conta.client.PortoCardClient;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.decodertoken.DecodificarAccessToken;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.pix.v2.client.PixManagementV2Client;
import com.porto.experiencia.cliente.conta.digital.commons.domain.exception.BusinessException;
import com.porto.experiencia.cliente.conta.digital.commons.domain.exception.FeignClientException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
class AccountManagementAdapterImplTest {


    @MockBean
    AccountManagementClient client;

    @MockBean
    PortoCardClient cardPortoClient;

    @Autowired
    AccountManagementAdapterImpl adapter;

    @MockBean
    PixManagementV2Client pixKeysClient;


    @MockBean
    DecodificarAccessToken tokenDecoder;

    @Test
    void testGetBalanceAccountWithRetry() {
        String xItauAuth = "authToken";
        String accountId = "12345";

        when(client.getBalanceAccount(anyString(), anyString(), anyString(), anyString()))
                .thenThrow(new FeignClientException(null,"404",null));


        assertThrows(BusinessException.class, () -> {
            adapter.getBalanceAccount(xItauAuth, accountId);
        });

        verify(client, times(3)).getBalanceAccount(anyString(), anyString(), anyString(), anyString());
    }

    @Test
    void testGetAccountDataWithRetry() {
        String xItauAuth = "authToken";
        String accountId = "12345";

        when(client.getAccountData(anyString(), anyString(), anyString(), anyString(), anyString()))
                .thenThrow(new FeignClientException(null, "404", null));

        assertThrows(BusinessException.class, () -> {
            adapter.getAccountData(xItauAuth, accountId);
        });

        verify(client, times(3)).getAccountData(anyString(), anyString(), anyString(), anyString(), anyString());
    }


    @Test
    void testGetSummaryAccountWithRetry() {
        String cognitoToken = "cognitoToken";
        String xItauAuth = "authToken";
        String accountId = "12345";

        when(client.getBalanceAccount(anyString(), anyString(), anyString(), anyString()))
                .thenThrow(new FeignClientException(null, "404", null));


        when(pixKeysClient.getPixKeyFromAnAccount(anyString(), anyString(), anyString()))
                .thenThrow(new FeignClientException(null, "404", null));


        when(client.getAccountData(anyString(), anyString(), anyString(), anyString(), anyString()))
                .thenThrow(new FeignClientException(null, "404", null));


        assertThrows(BusinessException.class, () -> {
            adapter.getSummaryAccount(cognitoToken, xItauAuth, accountId);
        });

        verify(client, times(3)).getBalanceAccount(anyString(), anyString(), anyString(), anyString());
        verify(pixKeysClient, times(3)).getPixKeyFromAnAccount(anyString(), anyString(), anyString());
        verify(client, times(3)).getAccountData(anyString(), anyString(), anyString(), anyString(), anyString());
        verify(cardPortoClient, times(3)).getCardsByuser(anyString());
    }

}