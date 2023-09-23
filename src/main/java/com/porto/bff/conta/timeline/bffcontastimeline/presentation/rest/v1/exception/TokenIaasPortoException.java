package com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.exception;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class TokenIaasPortoException extends RuntimeException {

    private String code;
    private List<TokenIaasPortoErroItem> errors;
    private String message;

    public TokenIaasPortoException(String message, String code, List<TokenIaasPortoErroItem> errors) {
        this.code = code;
        this.message = message;
        this.errors = errors;
    }

    @Data
    @Builder
    public static class TokenIaasPortoErroItem {
        private String field;
        private String message;
    }
}
