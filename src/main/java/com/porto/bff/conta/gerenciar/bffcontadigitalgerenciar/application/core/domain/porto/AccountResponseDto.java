package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.porto;

public record AccountResponseDto(
        String id,
        BankAccountDto contaBancaria,
        String statusConta,
        String tipo,
        String criadoEm,
        String atualizadoEm
) {
}

