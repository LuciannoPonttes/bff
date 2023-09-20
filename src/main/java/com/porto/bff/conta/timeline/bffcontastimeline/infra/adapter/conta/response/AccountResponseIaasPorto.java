package com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.conta.response;

import java.time.LocalDateTime;

public record AccountResponseIaasPorto(
        String id,
        BankAccountResponseIassPorto bankAccount,
        String status,
        String type,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
