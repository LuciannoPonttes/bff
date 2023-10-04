package com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto;

public record SaldoResponseDto(
        String contaId,
        String disponivel,
        String reservada,
        String bloqueado) {
}

