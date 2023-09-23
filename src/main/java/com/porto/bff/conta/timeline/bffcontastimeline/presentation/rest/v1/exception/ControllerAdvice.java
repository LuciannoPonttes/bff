package com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(PareamentoPortoException.class)
    public ResponseEntity<ResponseErrorApi> handlePareamentoException(PareamentoPortoException ex) {
        return ResponseEntity
                .status(HttpStatusCode.valueOf(407))
                .body(ResponseErrorApi.builder()
                        .code(ex.getCode())
                        .message(ex.getMessage())
                        .errors(convertPareamentoPortoErrors(ex.getErrors()))
                        .build());
    }

    @ExceptionHandler(TimelineIaasPortoException.class)
    public ResponseEntity<ResponseErrorApi> handleTokenIaaSException(TimelineIaasPortoException ex) {
        return ResponseEntity
                .status(HttpStatusCode.valueOf(407))
                .body(ResponseErrorApi.builder()
                        .code(ex.getCode())
                        .message(ex.getMessage())
                        .errors(convertTokenIaasPortoErrors(ex.getErrors()))
                        .build());
    }

    private List<ResponseErrorApi.ResponseErrorItem> convertPareamentoPortoErrors(List<PareamentoPortoException.PareamentoPortoErroItem> itemsError) {
        return itemsError.stream().map(itemError -> ResponseErrorApi.ResponseErrorItem.builder()
                .field(itemError.getField())
                .message(itemError.getMessage())
                .build()
        ).toList();
    }

    private List<ResponseErrorApi.ResponseErrorItem> convertTokenIaasPortoErrors(List<TimelineIaasPortoException.TimelineIaasPortoErroItem> itemsError) {
        return itemsError.stream().map(itemError -> ResponseErrorApi.ResponseErrorItem.builder()
                .field(itemError.getField())
                .message(itemError.getMessage())
                .build()
        ).toList();
    }

}
