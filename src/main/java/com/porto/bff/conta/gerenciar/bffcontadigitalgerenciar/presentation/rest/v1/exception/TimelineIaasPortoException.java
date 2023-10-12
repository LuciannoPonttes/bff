package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v1.exception;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class TimelineIaasPortoException extends RuntimeException{



    private List<TimelineIaasPortoErroItem> erros;

    public TimelineIaasPortoException( List<TimelineIaasPortoErroItem> erros) {
        this.erros = erros;
    }

    @Data
    @Builder
    public static class TimelineIaasPortoErroItem {
        private String campo;
        private List<String> mensagens;
    }
}
