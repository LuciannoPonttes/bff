package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v1.contas.dto;

public record SaldoResponseDto(
        String contaId,
        String disponivel,
        String reservada,
        String bloqueado) {
}

