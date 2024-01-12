package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v1.exception;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
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

    @Test
    public void testDataAnnotation() {
        TokenIaasPortoException.TokenIaasPortoErroItem erroItem1 = TokenIaasPortoException.TokenIaasPortoErroItem.builder().campo("campo").mensagens(Arrays.asList("mensagem")).build();
        TokenIaasPortoException.TokenIaasPortoErroItem erroItem2 = TokenIaasPortoException.TokenIaasPortoErroItem.builder().campo("campo").mensagens(Arrays.asList("mensagem")).build();
        List<TokenIaasPortoException.TokenIaasPortoErroItem> erros = Arrays.asList(erroItem1, erroItem2);

        TokenIaasPortoException exception1 = new TokenIaasPortoException(erros);
        TokenIaasPortoException exception2 = new TokenIaasPortoException(erros);

        assertThat(exception1).isEqualTo(exception1);
        assertThat(exception1.hashCode()).isEqualTo(exception1.hashCode());

        String toStringResult = exception1.toString();
        assertThat(toStringResult).contains("erros=");

        List<TokenIaasPortoException.TokenIaasPortoErroItem> retrievedErros = exception1.getErros();
        assertNull(retrievedErros);

        TokenIaasPortoException.TokenIaasPortoErroItem erroItem3 = TokenIaasPortoException.TokenIaasPortoErroItem.builder().campo("campo").mensagens(Arrays.asList("mensagem")).build();
        exception1.setErros(Arrays.asList(erroItem3));
        assertThat(exception1.getErros()).hasSize(1);
    }

}