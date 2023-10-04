package com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto;

public record ContaResponseDto(
        String id,
        ContaBancariaDto contaBancaria,
        String statusConta,
        String tipo,
        String criadoEm,
        String atualizadoEm
) {
}

