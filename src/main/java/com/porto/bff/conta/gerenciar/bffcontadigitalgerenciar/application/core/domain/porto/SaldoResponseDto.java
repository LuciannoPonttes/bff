package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.porto;

public record SaldoResponseDto(
        String contaId,
        String disponivel,
        String reservada,
        String bloqueado) {
}

