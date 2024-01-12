package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v1.exception;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
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

    @Test
    public void testDataAnnotation() {
        PareamentoPortoException.PareamentoPortoErroItem erroItem1 = PareamentoPortoException.PareamentoPortoErroItem.builder().field("field").message("message").build();
        PareamentoPortoException.PareamentoPortoErroItem erroItem2 = PareamentoPortoException.PareamentoPortoErroItem.builder().field("field").message("message").build();
        List<PareamentoPortoException.PareamentoPortoErroItem> errors = Arrays.asList(erroItem1, erroItem2);

        PareamentoPortoException exception1 = new PareamentoPortoException(errors);
        PareamentoPortoException exception2 = new PareamentoPortoException(errors);

        assertThat(exception1).isEqualTo(exception1);
        assertThat(exception1.hashCode()).isEqualTo(exception1.hashCode());

        String toStringResult = exception1.toString();
        assertThat(toStringResult).contains("erros=");

        List<PareamentoPortoException.PareamentoPortoErroItem> retrievedErrors = exception1.getErros();
        assertThat(retrievedErrors).hasSize(2);

        PareamentoPortoException.PareamentoPortoErroItem erroItem3 = PareamentoPortoException.PareamentoPortoErroItem.builder().field("field").message("message").build();
        exception1.setErros(Arrays.asList(erroItem3));
        assertThat(exception1.getErros()).hasSize(1);
    }
}