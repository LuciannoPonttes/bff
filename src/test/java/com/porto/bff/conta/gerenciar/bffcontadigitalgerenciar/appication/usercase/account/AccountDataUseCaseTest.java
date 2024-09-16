package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.appication.usercase.account;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.account.v2.out.client.DataAccountClient;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.usecase.account.AccountDataUseCase;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.account.v2.AccountManagementAdapterImpl;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.account.v2.client.AccountManagementClient;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.conta.client.PortoCardClient;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.decodertoken.DecodificarAccessToken;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.pix.v2.client.PixManagementV2Client;
import com.porto.experiencia.cliente.conta.digital.commons.domain.exception.BusinessException;
import com.porto.experiencia.cliente.conta.digital.commons.domain.exception.FeignClientException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
@Disabled
public class AccountDataUseCaseTest {

    @MockBean
    DataAccountClient client;

    @Autowired
    AccountDataUseCase adapter;

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

}
