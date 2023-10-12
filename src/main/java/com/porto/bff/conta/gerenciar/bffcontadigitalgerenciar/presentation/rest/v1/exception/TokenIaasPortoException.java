package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v1.exception;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
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
