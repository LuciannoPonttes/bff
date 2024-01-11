package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v1.exception;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;

class TokenIaasPortoExceptionTest {

    @Test
    void constructorAndGetters() {
        TokenIaasPortoException.TokenIaasPortoErroItem erroItem = mock(TokenIaasPortoException.TokenIaasPortoErroItem.class);
        List<TokenIaasPortoException.TokenIaasPortoErroItem> erros = List.of(erroItem);

        TokenIaasPortoException exception = new TokenIaasPortoException(erros);

        assertNull(exception.getErros());
    }

    @Test
    void tokenIaasPortoErroItemConstructorAndGetters() {
        String campo = "field";
        List<String> mensagens = List.of("error");

        TokenIaasPortoException.TokenIaasPortoErroItem erroItem = new TokenIaasPortoException.TokenIaasPortoErroItem(campo, mensagens);
        erroItem.setCampo(campo);
        assertThat(erroItem.getCampo()).isEqualTo(campo);
        assertThat(erroItem.getMensagens()).isEqualTo(mensagens);
    }

    @Test
    void tokenIaasPortoErroItemBuilder() {
        String campo = "field";
        List<String> mensagens = List.of("error");

        TokenIaasPortoException.TokenIaasPortoErroItem erroItem = TokenIaasPortoException.TokenIaasPortoErroItem.builder()
                .campo("field")
                .mensagens(List.of("error"))
                .build();


        assertThat(erroItem.getCampo()).isEqualTo(campo);
        assertThat(erroItem.getMensagens()).isEqualTo(mensagens);
    }

}