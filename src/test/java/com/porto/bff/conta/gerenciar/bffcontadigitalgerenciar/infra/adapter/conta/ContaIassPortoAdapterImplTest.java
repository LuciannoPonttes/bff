package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.conta;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.DataResponseIassPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.conta.AccountFlagsResponseIaasPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.conta.AccountResponseIaasPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.conta.BankAccountResponseIassPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.saldo.BalanceResponseIaasPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.conta.client.ContaIaasPortoClient;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.decodertoken.DecodificarAccessToken;
import feign.FeignException;
import feign.Request;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.cloud.openfeign.security.OAuth2AccessTokenInterceptor.BEARER;

@SpringBootTest
@TestPropertySource(properties = {"blocking-mock.activated=false", "blocking-mock.account=mock"})
class ContaIassPortoAdapterImplTest {

    @Autowired
    private ContaIassPortoAdapter adapter;
    @MockBean
    private ContaIaasPortoClient client;
    @MockBean
    private DecodificarAccessToken decodificador;
    private DataResponseIassPorto<AccountResponseIaasPorto> responseIassPorto;
    private DataResponseIassPorto<BalanceResponseIaasPorto> saldoResponse;
    private FeignException.FeignClientException feignClientException;

    @BeforeEach
    void setUp() {
        responseIassPorto = new DataResponseIassPorto<>(
                new AccountResponseIaasPorto(UUID.randomUUID().toString(),
                        new BankAccountResponseIassPorto(
                                "banco",
                                "agencia",
                                "numero",
                                "digito"
                        ),
                        "status",
                        "tipo",
                        List.of(
                                new AccountFlagsResponseIaasPorto(
                                        "123",
                                        "policy123",
                                        "dataHora")
                        ),
                        LocalDate.now().toString(),
                        LocalDate.now().toString())
        );
        saldoResponse = new DataResponseIassPorto<>(new BalanceResponseIaasPorto(
                UUID.randomUUID().toString(),
                10.0,
                0.0,
                0.0
        ));
        feignClientException = new FeignException.FeignClientException(
                401,
                HttpStatus.UNAUTHORIZED.name(),
                Request.create(
                        Request.HttpMethod.GET,
                        "http://localhost",
                        Map.of(),
                        Request.Body.create(new byte[]{}),
                        null),
                new byte[]{},
                Map.of()
        );
    }

    @Test
    @DisplayName("Deve obter dados da conta com sucesso")
    void getConta() {
        when(this.client.findByIdContaIaas(anyString(), anyString(), anyString(), anyString())).thenReturn(responseIassPorto);
        assertDoesNotThrow(() -> this.adapter.getConta(BEARER, UUID.randomUUID().toString()));
    }

    @Test
    @DisplayName("Deve obter saldo com sucesso")
    void getContaSaldo() {
        when(this.client.findBySaldoContaIaas(anyString(), anyString(), anyString(), anyString())).thenReturn(saldoResponse);
        assertDoesNotThrow(() -> this.adapter.getContaSaldo(BEARER, UUID.randomUUID().toString()));
    }

    @Test
    @DisplayName("Deve obter sumario com sucesso")
    void sumarioConta() {
        when(this.client.findBySaldoContaIaas(anyString(), anyString(), anyString(), anyString())).thenReturn(saldoResponse);
        when(this.client.findByIdContaIaas(anyString(), anyString(), anyString(), anyString())).thenReturn(responseIassPorto);
        assertDoesNotThrow(() -> this.adapter.sumarioConta(BEARER, UUID.randomUUID().toString(), UUID.randomUUID().toString()));
    }
}