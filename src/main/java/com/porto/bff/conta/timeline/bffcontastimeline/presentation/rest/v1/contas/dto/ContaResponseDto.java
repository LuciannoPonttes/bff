package com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto;

public record ContaResponseDto(
        String id,
        ContaBancariaDto contaBancaria,
        String status,
        String tipo,
        String criado,
        String atualizado
) {
}

