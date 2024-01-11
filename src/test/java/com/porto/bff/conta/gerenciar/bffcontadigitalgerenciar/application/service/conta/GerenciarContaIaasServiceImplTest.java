package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.service.conta;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.DataResponseIassPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.conta.AccountFlagsResponseIaasPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.conta.AccountResponseIaasPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.conta.BankAccountResponseIassPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.saldo.BalanceResponseIaasPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.sumario.SumarioResponseIaasPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.conta.ContaIassPortoAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.cloud.openfeign.security.OAuth2AccessTokenInterceptor.BEARER;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {"blocking-mock.activated=false", "blocking-mock.account=mock"})
class GerenciarContaIaasServiceImplTest {

    @Autowired
    private GerenciarContaIaasService service;
    @MockBean
    private ContaIassPortoAdapter adapter;
    private DataResponseIassPorto<AccountResponseIaasPorto> responseIassPorto;
    private DataResponseIassPorto<BalanceResponseIaasPorto> saldoIAASResponse;

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
        saldoIAASResponse = new DataResponseIassPorto<>(new BalanceResponseIaasPorto(
                UUID.randomUUID().toString(),
                10.0,
                0.0,
                0.0
        ));
    }

    @Test
    void getConta() {
        when(this.adapter.getConta(anyString(), anyString())).thenReturn(responseIassPorto);
        assertDoesNotThrow(() -> this.service.getConta(BEARER, UUID.randomUUID().toString()));
    }

    @Test
    void getContaSaldo() {
        when(this.adapter.getContaSaldo(anyString(), anyString())).thenReturn(
                saldoIAASResponse
        );
        assertDoesNotThrow(() -> this.service.getContaSaldo(BEARER, UUID.randomUUID().toString()));
    }

    @Test
    void contaSumario() {
        when(this.adapter.sumarioConta(anyString(), anyString(), anyString())).thenReturn(
                new DataResponseIassPorto<>(new SumarioResponseIaasPorto(
                        "12345678900",
                        this.responseIassPorto,
                        this.saldoIAASResponse,
                        false,
                        "Cadastre suas Chaves Pix"
                ))
        );
        assertDoesNotThrow(() -> this.service.contaSumario("Token", "xitauAuth", "contaId"));
    }
}