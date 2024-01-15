package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v1.exception;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class TimelineIaasPortoExceptionTest {

    @Test
    void constructorAndGetters() {
        TimelineIaasPortoException.TimelineIaasPortoErroItem erroItem = mock(TimelineIaasPortoException.TimelineIaasPortoErroItem.class);
        List<TimelineIaasPortoException.TimelineIaasPortoErroItem> erros = List.of(erroItem);

        TimelineIaasPortoException exception = new TimelineIaasPortoException(erros);

        assertThat(exception.getErros()).isEqualTo(erros);
    }

    @Test
    void timelineIaasPortoErroItemConstructorAndGetters() {
        String campo = "field";
        List<String> mensagens = List.of("error");

        TimelineIaasPortoException.TimelineIaasPortoErroItem erroItem = new TimelineIaasPortoException.TimelineIaasPortoErroItem(campo, mensagens);

        assertThat(erroItem.getCampo()).isEqualTo(campo);
        assertThat(erroItem.getMensagens()).isEqualTo(mensagens);
    }



    @Test
    void timelineIaasPortoErroItemBuilder() {
        String campo = "fieldName";
        List<String> mensagens = List.of("message1", "message2");

        TimelineIaasPortoException.TimelineIaasPortoErroItem erroItem = TimelineIaasPortoException.TimelineIaasPortoErroItem.builder()
                .campo(campo)
                .mensagens(mensagens)
                .build();
        erroItem.setCampo(campo);
        erroItem.setMensagens(mensagens);
        assertThat(erroItem.getCampo()).isEqualTo(campo);
        assertThat(erroItem.getMensagens()).isEqualTo(mensagens);
    }


    @Test
    public void testDataAnnotation() {
        TimelineIaasPortoException.TimelineIaasPortoErroItem erroItem1 = TimelineIaasPortoException.TimelineIaasPortoErroItem.builder().campo("campo").mensagens(Arrays.asList("mensagem")).build();
        TimelineIaasPortoException.TimelineIaasPortoErroItem erroItem2 = TimelineIaasPortoException.TimelineIaasPortoErroItem.builder().campo("campo").mensagens(Arrays.asList("mensagem")).build();
        List<TimelineIaasPortoException.TimelineIaasPortoErroItem> erros = Arrays.asList(erroItem1, erroItem2);

        TimelineIaasPortoException exception1 = new TimelineIaasPortoException(erros);
        TimelineIaasPortoException exception2 = new TimelineIaasPortoException(erros);

        assertThat(exception1).isEqualTo(exception1);
        assertThat(exception1.hashCode()).isEqualTo(exception1.hashCode());

        String toStringResult = exception1.toString();
        assertThat(toStringResult).contains("erros=");

        List<TimelineIaasPortoException.TimelineIaasPortoErroItem> retrievedErros = exception1.getErros();
        assertThat(retrievedErros).hasSize(2);

        TimelineIaasPortoException.TimelineIaasPortoErroItem erroItem3 = TimelineIaasPortoException.TimelineIaasPortoErroItem.builder().campo("campo").mensagens(Arrays.asList("mensagem")).build();
        exception1.setErros(Arrays.asList(erroItem3));
        assertThat(exception1.getErros()).hasSize(1);
    }

}