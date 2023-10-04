package com.porto.bff.conta.timeline.bffcontastimeline.domain.model.conta;

public record AccountResponseIaasPorto(
        String id,
        BankAccountResponseIassPorto bankAccount,
        String status,
        String type,
        String createdAt,
        String updatedAt
) {
}
