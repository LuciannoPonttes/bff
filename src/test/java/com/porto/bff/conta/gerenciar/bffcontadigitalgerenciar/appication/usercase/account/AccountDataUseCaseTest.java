package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.appication.usercase.account;

//import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.account.v2.client.AccountManagementClient;
import org.junit.jupiter.api.Disabled;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Disabled
public class AccountDataUseCaseTest {
/*
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
*/
}
