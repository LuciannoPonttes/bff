package com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.exception;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class TimelineIaasPortoException extends RuntimeException{


    private String code;
    private List<TimelineIaasPortoErroItem> errors;
    private String message;

    public TimelineIaasPortoException(String message, String code, List<TimelineIaasPortoErroItem> errors) {
        this.code = code;
        this.message = message;
        this.errors = errors;
    }

    @Data
    @Builder
    public static class TimelineIaasPortoErroItem {
        private String field;
        private String message;
    }
}
