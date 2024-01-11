package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v1.exception;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class PareamentoPortoExceptionTest {

    @Test
    void constructorAndGetters() {
        PareamentoPortoException.PareamentoPortoErroItem erroItem = mock(PareamentoPortoException.PareamentoPortoErroItem.class);
        List<PareamentoPortoException.PareamentoPortoErroItem> erros = List.of(erroItem);

        PareamentoPortoException exception = new PareamentoPortoException(erros);

        assertThat(exception.getErros()).isEqualTo(erros);
    }

    @Test
    void pareamentoPortoErroItemConstructorAndGetters() {
        String field = "field";
        String message = "error";

        PareamentoPortoException.PareamentoPortoErroItem erroItem = new PareamentoPortoException.PareamentoPortoErroItem(field, message);

        assertThat(erroItem.getField()).isEqualTo(field);
        assertThat(erroItem.getMessage()).isEqualTo(message);
    }

    @Test
    void pareamentoPortoErroItemBuilder() {
        String field = "fieldName";
        String message = "errorMessage";

        PareamentoPortoException.PareamentoPortoErroItem erroItem = PareamentoPortoException.PareamentoPortoErroItem.builder()
                .field(field)
                .message(message)
                .build();
        erroItem.setField(field);
        erroItem.setMessage(message);
        assertThat(erroItem.getField()).isEqualTo(field);
        assertThat(erroItem.getMessage()).isEqualTo(message);
    }
}