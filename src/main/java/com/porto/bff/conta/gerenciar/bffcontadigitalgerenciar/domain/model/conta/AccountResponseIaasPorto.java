package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.conta;

public record AccountResponseIaasPorto(
        String id,
        BankAccountResponseIassPorto bankAccount,
        String status,
        String type,
        String createdAt,
        String updatedAt
) {
}
