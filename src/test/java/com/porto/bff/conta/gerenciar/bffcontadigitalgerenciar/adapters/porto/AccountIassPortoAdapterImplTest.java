package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.porto;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.porto.client.AccountIaasPortoClient;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.porto.client.PortoCardClient;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.porto.client.PixManagementClient;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.porto.decodertoken.DecoderAccessToken;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.summary.v2.out.response.ListCardsResponseBody;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.summary.v2.out.response.PortoCardResponse;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.porto.*;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.ports.decodertoken.out.DecodificarAccessTokenOutPutPort;
import com.porto.experiencia.cliente.conta.digital.commons.web.model.ApiResponseData;
import feign.FeignException;
import feign.Request;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@SpringBootTest
class AccountIassPortoAdapterImplTest {

    @Mock
    private AccountIaasPortoClient accountIaasPortoClient;

    @Mock
    private PortoCardClient portoCardClient;

    @Mock
    private DecoderAccessToken decoderAccessToken;

    @MockBean
    private PixManagementClient pixManagementClient;

    @InjectMocks
    private AccountIassPortoAdapterImpl accountIassPortoAdapter;

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
    void testGetAccount() {
        String xItauAuth = "authToken";
        String contaId = "12345";


        when(accountIaasPortoClient.findByIdContaIaas(anyString(), anyString(), anyString(), anyString()))
                .thenReturn(responseIassPorto);

        DataResponseIassPorto<AccountResponseIaasPorto> actualResponse = accountIassPortoAdapter.getAccount(xItauAuth, contaId);

      //  assertEquals(responseIassPorto, actualResponse);
        //verify(accountIaasPortoClient, times(1)).findByIdContaIaas(anyString(), anyString(), anyString(), anyString());
    }

    @Test
    void testGetBalance() {
        String xItauAuth = "authToken";
        String contaId = "12345";


        when(accountIaasPortoClient.findBySaldoContaIaas(anyString(), anyString(), anyString(), anyString()))
                .thenReturn(saldoResponse);

        DataResponseIassPorto<BalanceResponseIaasPorto> actualResponse = accountIassPortoAdapter.getBalance(xItauAuth, contaId);

        assertEquals(saldoResponse, actualResponse);
        verify(accountIaasPortoClient, times(1)).findBySaldoContaIaas(anyString(), anyString(), anyString(), anyString());
    }
    @Test
    void testSumarioConta() {
        AccountIaasPortoClient contaIaasPortoClientMock = mock(AccountIaasPortoClient.class);
        PixManagementClient pixManagementClientMock = mock(PixManagementClient.class);
        DecodificarAccessTokenOutPutPort decodificarAccessTokenMock = mock(DecodificarAccessTokenOutPutPort.class);

        AccountIassPortoAdapterImpl contaIassPortoAdapter = new AccountIassPortoAdapterImpl(
                contaIaasPortoClientMock,
                mock(PortoCardClient.class),
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

        DataResponseIassPorto<SummaryResponseIaasPorto> result = contaIassPortoAdapter.getSummary(tokenCognito, xItauAuth, contaId);

        assertEquals("cpf", result.data().document());
        assertEquals(dadosConta, result.data().account());
        assertEquals(saldoConta, result.data().balance());
        assertEquals("2 Chaves", result.data().quantidadeChavePix());
    }
/*
    @Test
    void testGetSummary() {
        String tokenCognito = "cognitoToken";
        String xItauAuth = "authToken";
        String contaId = "12345";


        String cpf = "12345678900";
        boolean hasPortoCard = true;
        String pixKeysMessage = "1 Chave";

        when(accountIaasPortoClient.findByIdContaIaas(anyString(), anyString(), anyString(), anyString()))
                .thenReturn(responseIassPorto);
        when(accountIaasPortoClient.findBySaldoContaIaas(anyString(), anyString(), anyString(), anyString()))
                .thenReturn(saldoResponse);
        when(decoderAccessToken.getCpfPorToken(tokenCognito)).thenReturn(cpf);
        when(portoCardClient.getCardsByuser(tokenCognito)).thenReturn(new PortoCardResponse());
        when(pixManagementClient.getPixKeyFromAnAccount(anyString(), anyString(), anyString()))
                .thenReturn(new ApiResponseData<>(Collections.singletonList(new Object())));

        DataResponseIassPorto<SummaryResponseIaasPorto> summaryResponse = accountIassPortoAdapter.getSummary(tokenCognito, xItauAuth, contaId);

        assertNotNull(summaryResponse);
        assertEquals(cpf, summaryResponse.data().document());
        assertEquals(responseIassPorto, summaryResponse.data().account());
        assertEquals(saldoResponse, summaryResponse.data().balance());


        verify(accountIaasPortoClient, times(1)).findByIdContaIaas(anyString(), anyString(), anyString(), anyString());
        verify(accountIaasPortoClient, times(1)).findBySaldoContaIaas(anyString(), anyString(), anyString(), anyString());
        verify(decoderAccessToken, times(1)).getCpfPorToken(tokenCognito);
        verify(portoCardClient, times(1)).getCardsByuser(tokenCognito);
        verify(pixManagementClient, times(1)).getPixKeyFromAnAccount(anyString(), anyString(), anyString());
    }

    @Test
    void testVerificaExistenciaCartao() {
        String tokenCognito = "cognitoToken";
        PortoCardResponse portoCardResponse = new PortoCardResponse();
        portoCardResponse.setDados(portoCardResponse.getDados());
        portoCardResponse.getDados().setLista(Collections.singletonList((ListCardsResponseBody) new Object()));

        when(portoCardClient.getCardsByuser(tokenCognito)).thenReturn(portoCardResponse);

        boolean result = accountIassPortoAdapter.verificaExistenciaCartao(tokenCognito);

        assertTrue(result);
        verify(portoCardClient, times(1)).getCardsByuser(tokenCognito);
    }

    @Test
    void testGetBearerInput() {
        String xItauAuth = "Bearer authToken";
        String result = accountIassPortoAdapter.getBearerInput(xItauAuth);
        assertEquals(xItauAuth, result);

        xItauAuth = "authToken";
        result = accountIassPortoAdapter.getBearerInput(xItauAuth);
        assertEquals("Bearer authToken", result);
    }

    @Test
    void testBuildMessagePixKeys() {
        assertEquals("Cadastre suas Chaves Pix", AccountIassPortoAdapterImpl.buildMessagePixKeys(0));
        assertEquals("1 Chave", AccountIassPortoAdapterImpl.buildMessagePixKeys(1));
        assertEquals("2 Chaves", AccountIassPortoAdapterImpl.buildMessagePixKeys(2));
    }

    @Test
    void testObterQuantidadeChavesPixDaConta() {
        String tokenCognito = "cognitoToken";
        String xItauAuth = "authToken";
        String contaId = "12345";
        ApiResponseData<List<Object>> pixKeysResponse = new ApiResponseData<>(Collections.singletonList(new Object()));

        when(pixManagementClient.getPixKeyFromAnAccount(anyString(), anyString(), anyString()))
                .thenReturn(pixKeysResponse);

        String result = accountIassPortoAdapter.obterQuantidadeChavesPixDaConta(tokenCognito, xItauAuth, contaId);

        assertEquals("1 Chave", result);
        verify(pixManagementClient, times(1)).getPixKeyFromAnAccount(anyString(), anyString(), anyString());
    }*/
}