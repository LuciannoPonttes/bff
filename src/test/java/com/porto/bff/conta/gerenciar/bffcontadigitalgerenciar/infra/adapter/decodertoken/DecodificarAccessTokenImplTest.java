package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.decodertoken;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.porto.decodertoken.DecoderAccessTokenImpl;
import com.porto.experiencia.cliente.conta.digital.commons.domain.exception.BusinessException;
import com.porto.experiencia.cliente.conta.digital.commons.web.tokendecoder.AccessTokenDecoder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DecodificarAccessTokenImplTest {


    private AccessTokenDecoder decoder;
    private DecoderAccessTokenImpl decodificarAccessToken;

    @BeforeEach
    public void setUp() {
        decoder = mock(AccessTokenDecoder.class);
        decodificarAccessToken = new DecoderAccessTokenImpl(decoder);
    }

    @Test
    public void testGetCpfPorTokenSuccess() {
        String accessToken = "validAccessToken";
        String expectedCpf = "11122233344";
        when(decoder.getCpf(accessToken)).thenReturn(java.util.Optional.of(expectedCpf));

        String result = decodificarAccessToken.getCpfPorToken(accessToken);

        assertEquals(expectedCpf, result);
    }

    @Test
    public void testGetCpfPorTokenFailure() {
        String accessToken = "invalidAccessToken";
        when(decoder.getCpf(accessToken)).thenReturn(java.util.Optional.empty());

        BusinessException exception = assertThrows(BusinessException.class, () ->
                decodificarAccessToken.getCpfPorToken(accessToken)
        );

        assertEquals(400, exception.getStatusCode());
        assertEquals("ERRO_DECODIFICAR_DOCUMENTO:Não foi possível decodificar o documento", exception.getMessage());
    }
}