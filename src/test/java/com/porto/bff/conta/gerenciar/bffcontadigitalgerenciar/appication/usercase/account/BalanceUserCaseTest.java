package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.appication.usercase.account;

import org.junit.jupiter.api.Disabled;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Disabled
public class BalanceUserCaseTest {
/*
    @MockBean
    DataBalanceClient client;

    @Autowired
    BalanceUserCaseService adapter;


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
    }*/
}
