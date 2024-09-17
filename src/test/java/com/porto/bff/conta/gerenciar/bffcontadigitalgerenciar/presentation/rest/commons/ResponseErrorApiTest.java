package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.commons;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.common.ResponseErrorApi;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

    @Test
    public void testResponseErrorApiConstructor() {
        ResponseErrorApi.ResponseErrorItem errorItem1 = ResponseErrorApi.ResponseErrorItem.builder()
                .campo("campo1")
                .mensagens(Arrays.asList("mensagem1", "mensagem2"))
                .build();

        ResponseErrorApi.ResponseErrorItem errorItem2 = ResponseErrorApi.ResponseErrorItem.builder()
                .campo("campo2")
                .mensagens(Collections.singletonList("mensagem3"))
                .build();

        List<ResponseErrorApi.ResponseErrorItem> errorItems = Arrays.asList(errorItem1, errorItem2);

        ResponseErrorApi responseErrorApi = new ResponseErrorApi(errorItems);

        assertThat(responseErrorApi.getErros()).isEqualTo(errorItems);
    }

    @Test
    public void testResponseErrorItemBuilder() {
        ResponseErrorApi.ResponseErrorItem errorItem = ResponseErrorApi.ResponseErrorItem.builder()
                .campo("campo")
                .mensagens(Arrays.asList("mensagem1", "mensagem2"))
                .build();

        assertThat(errorItem.getCampo()).isEqualTo("campo");
        assertThat(errorItem.getMensagens()).containsExactly("mensagem1", "mensagem2");
    }

    @Test
    public void testResponseErrorApiBuilder() {
        ResponseErrorApi.ResponseErrorItem errorItem = ResponseErrorApi.ResponseErrorItem.builder()
                .campo("campo")
                .mensagens(Arrays.asList("mensagem1", "mensagem2"))
                .build();

        ResponseErrorApi responseErrorApi = ResponseErrorApi.builder()
                .erros(Collections.singletonList(errorItem))
                .build();

        assertThat(responseErrorApi.getErros()).containsExactly(errorItem);
    }

    @Test
    void testEqualsAndHashCode() {
        ResponseErrorApi.ResponseErrorItem errorItem1 = ResponseErrorApi.ResponseErrorItem.builder()
                .campo("fieldName")
                .mensagens(List.of("message1", "message2"))
                .build();

        ResponseErrorApi.ResponseErrorItem errorItem2 = ResponseErrorApi.ResponseErrorItem.builder()
                .campo("fieldName")
                .mensagens(List.of("message1", "message2"))
                .build();
        assertThat(errorItem1).isEqualTo(errorItem2);
        assertThat(errorItem1.hashCode()).isEqualTo(errorItem2.hashCode());
        ResponseErrorApi responseErrorApi1 = new ResponseErrorApi(List.of(errorItem1));
        ResponseErrorApi responseErrorApi2 = new ResponseErrorApi(List.of(errorItem2));
        assertThat(responseErrorApi1).isEqualTo(responseErrorApi2);
        assertThat(responseErrorApi1.hashCode()).isEqualTo(responseErrorApi2.hashCode());
    }
    @Test
    public void testToString() {

        ResponseErrorApi.ResponseErrorItem item = ResponseErrorApi.ResponseErrorItem.builder()
                .campo("campo1")
                .mensagens(Arrays.asList("mensagem1", "mensagem2"))
                .build();
        ResponseErrorApi responseErrorApi = ResponseErrorApi.builder()
                .erros(Arrays.asList(item))
                .build();
        String toStringOutput = responseErrorApi.toString();
        assertNotNull(toStringOutput);
    }

}