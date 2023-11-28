package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.conta;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.DataResponseIassPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.conta.AccountResponseIaasPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.conta.BankAccountResponseIassPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.saldo.BalanceResponseIaasPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.conta.client.ContaIaasPortoClient;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.decodertoken.DecodificarAccessToken;
import com.porto.experiencia.cliente.conta.digital.commons.domain.exception.BusinessException;
import feign.FeignException;
import feign.Request;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.cloud.openfeign.security.OAuth2AccessTokenInterceptor.BEARER;

@SpringBootTest
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
<<<<<<< Updated upstream
=======
    @DisplayName("Deve gerar erro ao gerar dados da conta")
    void getConta1() {
        when(this.client.findByIdContaIaas(anyString(), anyString(), anyString(), anyString())).thenThrow(
                FeignException.FeignClientException.class);
        var exception = assertThrows(BusinessException.class, () -> this.adapter.getConta(BEARER, UUID.randomUUID().toString()));
        //assertEquals("GET_CONTA_ERROR", exception.getCampo());
    }

    @Test
    @DisplayName("Deve gerar erro de token ao gerar dados da conta")
    void getConta2() {
        when(this.client.findByIdContaIaas(anyString(), anyString(), anyString(), anyString())).thenThrow(feignClientException);
        var exception = assertThrows(BusinessException.class, () -> this.adapter.getConta(BEARER, UUID.randomUUID().toString()));
       // assertEquals("IAAS_EXPIRATION_TOKEN", exception.getCampo());
    }

    @Test
>>>>>>> Stashed changes
    @DisplayName("Deve obter saldo com sucesso")
    void getContaSaldo() {
        when(this.client.findBySaldoContaIaas(anyString(), anyString(), anyString(), anyString())).thenReturn(saldoResponse);
        assertDoesNotThrow(() -> this.adapter.getContaSaldo(BEARER, UUID.randomUUID().toString()));
    }

    @Test
<<<<<<< Updated upstream
=======
    @DisplayName("Deve gerar erro ao obter saldo")
    void getContaSaldo1() {
        when(this.client.findBySaldoContaIaas(anyString(), anyString(), anyString(), anyString())).thenThrow(
                FeignException.FeignClientException.class);
        var exception = assertThrows(BusinessException.class, () ->
                this.adapter.getContaSaldo(BEARER, UUID.randomUUID().toString()));
      //  assertEquals("GET_SALDO_CONTA_ERROR", exception.getCampo());
    }

    @Test
    @DisplayName("Deve gerar erro de token ao pegar saldo da conta")
    void getContaSaldo2() {
        when(this.client.findBySaldoContaIaas(anyString(), anyString(), anyString(), anyString())).thenThrow(feignClientException);
        var exception = assertThrows(BusinessException.class, () -> this.adapter.getContaSaldo(BEARER, UUID.randomUUID().toString()));
     //   assertEquals("IAAS_EXPIRATION_TOKEN", exception.getCampo());
    }

    @Test
>>>>>>> Stashed changes
    @DisplayName("Deve obter sumario com sucesso")
    void sumarioConta() {
        when(this.client.findBySaldoContaIaas(anyString(), anyString(), anyString(), anyString())).thenReturn(saldoResponse);
        when(this.client.findByIdContaIaas(anyString(), anyString(), anyString(), anyString())).thenReturn(responseIassPorto);
        assertDoesNotThrow(() -> this.adapter.sumarioConta(BEARER, UUID.randomUUID().toString(), UUID.randomUUID().toString()));
    }
<<<<<<< Updated upstream
=======

    @Test
    @DisplayName("Deve gerar erro ao obter sumario")
    void sumarioConta1() {
        when(this.client.findBySaldoContaIaas(anyString(), anyString(), anyString(), anyString())).thenReturn(saldoResponse);
        when(this.client.findByIdContaIaas(anyString(), anyString(), anyString(), anyString())).thenThrow(
                FeignException.FeignClientException.class);
        var exception = assertThrows(BusinessException.class, () ->
                this.adapter.sumarioConta(BEARER, UUID.randomUUID().toString(), UUID.randomUUID().toString()));
       // assertEquals("GET_SUMARIO_CONTA_ERROR", exception.getCampo());
    }

    @Test
    @DisplayName("Deve gerar erro de token ao obter sumario")
    void sumarioConta2() {
        when(this.client.findBySaldoContaIaas(anyString(), anyString(), anyString(), anyString())).thenReturn(saldoResponse);
        when(this.client.findByIdContaIaas(anyString(), anyString(), anyString(), anyString())).thenThrow(feignClientException);
        var exception = assertThrows(BusinessException.class, () ->
                this.adapter.sumarioConta(BEARER, UUID.randomUUID().toString(), UUID.randomUUID().toString()));
      //  assertEquals("IAAS_EXPIRATION_TOKEN", exception.getCampo());
    }
>>>>>>> Stashed changes
}