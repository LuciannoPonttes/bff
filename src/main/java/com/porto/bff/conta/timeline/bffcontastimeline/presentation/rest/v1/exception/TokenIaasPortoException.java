package com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.exception;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class TokenIaasPortoException extends RuntimeException {

    private List<TokenIaasPortoErroItem> erros;

    public TokenIaasPortoException( List<TokenIaasPortoErroItem> errors) {
        this.erros = erros;
    }

    @Data
    @Builder
    public static class TokenIaasPortoErroItem {
        private String campo;
        private List<String> mensagens;
    }
}
