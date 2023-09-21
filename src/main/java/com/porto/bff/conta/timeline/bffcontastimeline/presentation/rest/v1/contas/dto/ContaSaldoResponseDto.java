package com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto;

public record ContaSaldoResponseDto(String contaId, int disponivel, int reservada, int bloqueado) {
}

