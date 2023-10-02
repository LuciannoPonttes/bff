package com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.conta.response;

public record AccountResponseIaasPorto(
        String id,
        BankAccountResponseIassPorto bankAccount,
        String status,
        String type,
        String createdAt,
        String updatedAt
) {
}
