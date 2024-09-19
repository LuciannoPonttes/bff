package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.appication.usercase.account;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.account.v2.out.client.DataAccountAdapter;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.account.v2.out.client.DataAccountClient;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.porto.client.PixManagementV2Client;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.summary.v2.out.client.CardClient;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.account.AccountDataEntityResponseDomain;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.account.BankAccount;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.account.BankAccountDomain;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.backend.BackendResponseDataDomain;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.balance.BalanceEntityResponseDomain;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.usecase.AccountManagementUserCase;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.ports.account.out.DataAccountClientOutPort;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.ports.balance.out.DataBalanceClientOutPort;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.ports.decodertoken.out.DecodificarAccessTokenOutPutPort;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.common.utils.HeaderValidation;
import com.porto.experiencia.cliente.conta.digital.commons.domain.exception.BusinessException;
import com.porto.experiencia.cliente.conta.digital.commons.domain.exception.FeignClientException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.ExpectedCount.times;
@SpringBootTest
public class AccountManagementUserCaseTest {



    @Mock
    private HeaderValidation headerValidation;

    @Mock
    private DataAccountClientOutPort client;
    @Mock
    DataBalanceClientOutPort clientBalance;

    @Mock
    private CardClient cardPortoClient;

    @Mock
    private PixManagementV2Client pixKeysClient;

    @Mock
    private DecodificarAccessTokenOutPutPort tokenDecoder;

    @InjectMocks
    private AccountManagementUserCase accountManagementUserCase;

   

    @Test
    void testGetAccountData() {
        // Arrange
        String xItauAuth = "auth-token";
        String accountId = "account-id";
        var bankAccount = new BankAccountDomain("", "", "", "", "", "");

        AccountDataEntityResponseDomain accountData = new AccountDataEntityResponseDomain("",bankAccount,"","","","");

        when(client.getAccountData(anyString(), anyString(), anyString(), anyString(), anyString()))
               .thenReturn(accountData);

        // Act
        BackendResponseDataDomain<AccountDataEntityResponseDomain> result = accountManagementUserCase.getAccountData(xItauAuth, accountId);

        // Assert
        assertNotNull(result);
        assertEquals(accountData, result.data());
        verify(headerValidation).isValidHeaderProjet(xItauAuth, accountId);
        verify(client).getAccountData(anyString(), anyString(), anyString(), anyString(), anyString());
    }

    @Test
    void testGetBalanceAccount() {
        // Arrange
        String xItauAuth = "authToken";
        String accountId = "accountId";
        BalanceEntityResponseDomain balanceData = new BalanceEntityResponseDomain(10.0, 10.0, 10.0);


        when(clientBalance.getBalanceAccount(anyString(), anyString(), anyString(), anyString()))
                .thenReturn(balanceData);

        // Act
        BackendResponseDataDomain<BalanceEntityResponseDomain> result = accountManagementUserCase.getBalanceAccount(xItauAuth, accountId);

        // Assert
        assertNotNull(result);
        assertEquals(balanceData, result.data());
        verify(headerValidation).isValidHeaderProjet(xItauAuth, accountId);
        verify(clientBalance).getBalanceAccount(anyString(), anyString(), anyString(), anyString());
    }




}
