package com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.exception;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class PareamentoPortoException extends RuntimeException {

    private String code;
    private List<PareamentoPortoErroItem> errors;
    private String message;

    public PareamentoPortoException(String message, String code, List<PareamentoPortoErroItem> errors) {
        this.code = code;
        this.message = message;
        this.errors = errors;
    }

    @Data
    @Builder
    public static class PareamentoPortoErroItem {
        private String field;
        private String message;
    }
}
