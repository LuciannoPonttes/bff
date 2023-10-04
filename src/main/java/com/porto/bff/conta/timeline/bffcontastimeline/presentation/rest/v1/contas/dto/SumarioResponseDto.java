package com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto;

public record SumarioResponseDto(
        String nomeBanco,
        String ispb,
        String agencia,
        String numeroConta,
        String digitoConta,
        String saldo,
        String statusConta,
        String documento

) {
}
