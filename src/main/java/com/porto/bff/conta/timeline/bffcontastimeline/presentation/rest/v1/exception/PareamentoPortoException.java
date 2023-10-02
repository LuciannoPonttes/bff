package com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.exception;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class PareamentoPortoException extends RuntimeException {


    private List<PareamentoPortoErroItem> erros;

    public PareamentoPortoException( List<PareamentoPortoErroItem> errors) {
        this.erros = errors;
    }

    @Data
    @Builder
    public static class PareamentoPortoErroItem {
        private String field;
        private String message;
    }
}
