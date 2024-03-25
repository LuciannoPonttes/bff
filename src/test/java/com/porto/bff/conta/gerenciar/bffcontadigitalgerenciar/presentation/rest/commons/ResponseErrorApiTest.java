package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.commons;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ResponseErrorApiTest {

    @Test
    void constructorAndGetters() {
        ResponseErrorApi.ResponseErrorItem errorItem = ResponseErrorApi.ResponseErrorItem.builder()
                .campo("fieldName")
                .mensagens(List.of("message1", "message2"))
                .build();
        List<ResponseErrorApi.ResponseErrorItem> errors = List.of(errorItem);

        ResponseErrorApi responseErrorApi = new ResponseErrorApi(errors);
        responseErrorApi.setErros(errors);

        assertThat(responseErrorApi.getErros()).isEqualTo(errors);
    }

    @Test
    void responseErrorItemBuilder() {
        String campo = "fieldName";
        List<String> mensagens = List.of("message1", "message2");

        ResponseErrorApi.ResponseErrorItem errorItem = ResponseErrorApi.ResponseErrorItem.builder()
                .campo(campo)
                .mensagens(mensagens)
                .build();

        errorItem.setCampo(campo);
        errorItem.setMensagens(mensagens);

        assertThat(errorItem.getCampo()).isEqualTo(campo);
        assertThat(errorItem.getMensagens()).isEqualTo(mensagens);
    }


}