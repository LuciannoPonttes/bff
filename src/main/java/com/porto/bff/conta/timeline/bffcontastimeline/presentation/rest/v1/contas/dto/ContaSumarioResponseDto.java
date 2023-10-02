package com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto;

public record ContaSumarioResponseDto(
        String nomeBanco,
        String codigoBanco,
        String agencia,
        String conta,
        String saldo,
        String statusConta,
        String documento

) {
}
