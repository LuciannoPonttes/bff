package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.conta;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.DataResponseIassPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.conta.AccountFlagsResponseIaasPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.conta.AccountResponseIaasPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.conta.BankAccountResponseIassPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.saldo.BalanceResponseIaasPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.sumario.SumarioResponseIaasPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.conta.client.CartoesPortoClient;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.conta.client.ContaIaasPortoClient;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.decodertoken.DecodificarAccessToken;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.pix.client.PixManagementClient;
import com.porto.experiencia.cliente.conta.digital.commons.web.model.ApiResponseData;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
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


    @Test
    void testSumarioConta() {
        ContaIaasPortoClient contaIaasPortoClientMock = mock(ContaIaasPortoClient.class);
        PixManagementClient pixManagementClientMock = mock(PixManagementClient.class);
        DecodificarAccessToken decodificarAccessTokenMock = mock(DecodificarAccessToken.class);

        ContaIassPortoAdapterImpl contaIassPortoAdapter = new ContaIassPortoAdapterImpl(
                contaIaasPortoClientMock,
                mock(CartoesPortoClient.class),
                decodificarAccessTokenMock,
                pixManagementClientMock
        );

        String tokenCognito = "token";
        String xItauAuth = "xItauAuth";
        String contaId = "contaId";

        DataResponseIassPorto<AccountResponseIaasPorto> dadosConta = new DataResponseIassPorto<>(mock());
        DataResponseIassPorto<BalanceResponseIaasPorto> saldoConta = new DataResponseIassPorto<>(mock());
        when(contaIaasPortoClientMock.findByIdContaIaas(any(), any(), any(), any())).thenReturn(dadosConta);
        when(contaIaasPortoClientMock.findBySaldoContaIaas(any(), any(), any(), any())).thenReturn(saldoConta);
        when(decodificarAccessTokenMock.getCpfPorToken(anyString())).thenReturn("cpf");

        ApiResponseData<List<Object>> listChavePix = new ApiResponseData<>(List.of("chave1", "chave2"));
        when(pixManagementClientMock.getPixKeyFromAnAccount(any(), any(), any())).thenReturn(listChavePix);

        DataResponseIassPorto<SumarioResponseIaasPorto> result = contaIassPortoAdapter.sumarioConta(tokenCognito, xItauAuth, contaId);

        assertEquals("cpf", result.data().document());
        assertEquals(dadosConta, result.data().account());
        assertEquals(saldoConta, result.data().balance());
        assertEquals("2 Chaves", result.data().quantidadeChavePix());
    }


    @Test
    void testSumarioContaBearer() {
        ContaIaasPortoClient contaIaasPortoClientMock = mock(ContaIaasPortoClient.class);
        PixManagementClient pixManagementClientMock = mock(PixManagementClient.class);
        DecodificarAccessToken decodificarAccessTokenMock = mock(DecodificarAccessToken.class);

        ContaIassPortoAdapterImpl contaIassPortoAdapter = new ContaIassPortoAdapterImpl(
                contaIaasPortoClientMock,
                mock(CartoesPortoClient.class),
                decodificarAccessTokenMock,
                pixManagementClientMock
        );

        String tokenCognito = "token";
        String xItauAuth = "Bearer xItauAuth";
        String contaId = "contaId";

        DataResponseIassPorto<AccountResponseIaasPorto> dadosConta = new DataResponseIassPorto<>(mock());
        DataResponseIassPorto<BalanceResponseIaasPorto> saldoConta = new DataResponseIassPorto<>(mock());
        when(contaIaasPortoClientMock.findByIdContaIaas(any(), any(), any(), any())).thenReturn(dadosConta);
        when(contaIaasPortoClientMock.findBySaldoContaIaas(any(), any(), any(), any())).thenReturn(saldoConta);
        when(decodificarAccessTokenMock.getCpfPorToken(anyString())).thenReturn("cpf");

        ApiResponseData<List<Object>> listChavePix = new ApiResponseData<>(List.of("chave1", "chave2"));
        when(pixManagementClientMock.getPixKeyFromAnAccount(any(), any(), any())).thenReturn(listChavePix);

        DataResponseIassPorto<SumarioResponseIaasPorto> result = contaIassPortoAdapter.sumarioConta(tokenCognito, xItauAuth, contaId);

        assertEquals("cpf", result.data().document());
        assertEquals(dadosConta, result.data().account());
        assertEquals(saldoConta, result.data().balance());
        assertEquals("2 Chaves", result.data().quantidadeChavePix());
    }



    @Test
    void testFormatarMensagemParaExbirNoFront0() {
        PixManagementClient pixManagementClientMock = mock(PixManagementClient.class);
        when(pixManagementClientMock.getPixKeyFromAnAccount(any(), any(), any())).thenThrow(new RuntimeException("Some error"));
        ContaIassPortoAdapterImpl contaIassPortoAdapter = new ContaIassPortoAdapterImpl(
                mock(ContaIaasPortoClient.class),
                mock(CartoesPortoClient.class),
                mock(DecodificarAccessToken.class),
                pixManagementClientMock
        );

        String result = contaIassPortoAdapter.formatarMensagemParaExbirNoFront(0);

        assertEquals("Cadastre suas Chaves Pix", result);
    }

    @Test
    void testFormatarMensagemParaExbirNoFront1() {
        PixManagementClient pixManagementClientMock = mock(PixManagementClient.class);
        when(pixManagementClientMock.getPixKeyFromAnAccount(any(), any(), any())).thenThrow(new RuntimeException("Some error"));
        ContaIassPortoAdapterImpl contaIassPortoAdapter = new ContaIassPortoAdapterImpl(
                mock(ContaIaasPortoClient.class),
                mock(CartoesPortoClient.class),
                mock(DecodificarAccessToken.class),
                pixManagementClientMock
        );

        String result = contaIassPortoAdapter.formatarMensagemParaExbirNoFront(1);

        assertEquals("1 Chave", result);
    }

    @Test
    void testFormatarMensagemParaExbirNoFront2() {
        PixManagementClient pixManagementClientMock = mock(PixManagementClient.class);
        when(pixManagementClientMock.getPixKeyFromAnAccount(any(), any(), any())).thenThrow(new RuntimeException("Some error"));
        ContaIassPortoAdapterImpl contaIassPortoAdapter = new ContaIassPortoAdapterImpl(
                mock(ContaIaasPortoClient.class),
                mock(CartoesPortoClient.class),
                mock(DecodificarAccessToken.class),
                pixManagementClientMock
        );

        String result = contaIassPortoAdapter.formatarMensagemParaExbirNoFront(2);

        assertEquals("2 Chaves", result);
    }

    @Test
    void testObterQuantidadeChavesPixDaConta_Exception() {
        PixManagementClient pixManagementClientMock = mock(PixManagementClient.class);
        when(pixManagementClientMock.getPixKeyFromAnAccount(any(), any(), any())).thenThrow(new RuntimeException("Some error"));

        ContaIassPortoAdapterImpl contaIassPortoAdapter = new ContaIassPortoAdapterImpl(
                mock(ContaIaasPortoClient.class),
                mock(CartoesPortoClient.class),
                mock(DecodificarAccessToken.class),
                pixManagementClientMock
        );

        String result = contaIassPortoAdapter.obterQuantidadeChavesPixDaConta("token", "xItauAuth", "contaId");

        assertEquals("", result); // Make sure the expected result is returned when an exception occurs
    }

}