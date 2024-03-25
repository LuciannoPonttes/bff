package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.commons;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResponseErrorApi {


    private List<ResponseErrorItem> erros;

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
