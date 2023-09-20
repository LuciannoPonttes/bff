package com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto;

import java.time.LocalDateTime;

public record ContaResponseDto(
        String id,
        ContaBancariaDto contaBancaria,
        String status,
        String tipo,
        LocalDateTime criando,
        LocalDateTime atualizado
) {
}

