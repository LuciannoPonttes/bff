package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.saldo;

public record BalanceResponseIaasPorto(
        String accountId,
        Double available,
        Double reserved,
        Double blocked
) {
}
