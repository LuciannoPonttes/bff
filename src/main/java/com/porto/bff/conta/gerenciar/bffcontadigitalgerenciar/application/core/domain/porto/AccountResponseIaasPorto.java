package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.porto;

import lombok.With;

import java.util.List;

public record AccountResponseIaasPorto(
        String id,
        BankAccountResponseIassPorto bankAccount,
        String status,
        String type,
        @With
        List<AccountFlagsResponseIaasPorto> flags,
        String createdAt,
        String updatedAt
) {
}
