package com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(PareamentoPortoException.class)
    public ResponseEntity<ResponseErrorApi> handlePareamentoException(PareamentoPortoException ex) {
        return ResponseEntity
                .status(HttpStatusCode.valueOf(407))
                .body(ResponseErrorApi.builder()
                        .erros(convertPareamentoPortoErrors(ex.getErros()))
                        .build());
    }

    @ExceptionHandler(TimelineIaasPortoException.class)
    public ResponseEntity<ResponseErrorApi> handleTokenIaaSException(TimelineIaasPortoException ex) {
        return ResponseEntity
                .status(HttpStatusCode.valueOf(500)) // TODO: 29/09/2023 ALTERAR PARA 407
                .body(ResponseErrorApi.builder()
                        .erros(convertTokenIaasPortoErrors(ex.getErros()))
                        .build());
    }

    private List<ResponseErrorApi.ResponseErrorItem> convertPareamentoPortoErrors(List<PareamentoPortoException.PareamentoPortoErroItem> itemsError) {
        return itemsError.stream().map(itemError -> ResponseErrorApi.ResponseErrorItem.builder()
                .campo(itemError.getField())
                .mensagens(getMensagenError(itemError.getMessage()))
                .build()
        ).toList();
    }

    private List<ResponseErrorApi.ResponseErrorItem> convertTokenIaasPortoErrors(List<TimelineIaasPortoException.TimelineIaasPortoErroItem> itemsError) {
        return itemsError.stream().map(itemError -> ResponseErrorApi.ResponseErrorItem.builder()
                .campo(itemError.getCampo())
                .mensagens(getMensagenError(itemError.getMensagens().get(0)))
                .build()
        ).toList();
    }

    private List<String> getMensagenError(String msg) {
        List<String> mensagen = new ArrayList<>();
        mensagen.add(msg);
        return mensagen;
    }
}
