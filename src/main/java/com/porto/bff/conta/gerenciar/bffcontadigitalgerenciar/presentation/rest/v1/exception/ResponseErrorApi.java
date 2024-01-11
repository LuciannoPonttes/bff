package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v1.exception;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class ResponseErrorApi {


    private List<ResponseErrorItem> erros = new ArrayList<>();

    public ResponseErrorApi(List<ResponseErrorItem> errors) {
        this.erros = errors;
    }

    @Data
    @Builder
    public static class ResponseErrorItem {

        private String campo;
        private List<String> mensagens;

    }
}
