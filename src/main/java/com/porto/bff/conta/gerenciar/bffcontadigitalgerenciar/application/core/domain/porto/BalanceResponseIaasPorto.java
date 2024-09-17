package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.porto;

public record BalanceResponseIaasPorto(
        String accountId,
        Double available,
        Double reserved,
        Double blocked
) {
}
