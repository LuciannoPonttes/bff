package com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.exception;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class ResponseErrorApi {

//    private String code;
//    private String message;
    private List<ResponseErrorItem> erros = new ArrayList<>();

//    public ResponseErrorApi(String message, String code) {
//        this.code = code;
//        this.message = message;
//    }

    public ResponseErrorApi(List<ResponseErrorItem> errors) {
//        this.code = code;
//        this.message = message;
        this.erros = errors;
    }

    @Data
    @Builder
    public static class ResponseErrorItem {

        private String campo;
        private List<String> mensagens;

    }
}
