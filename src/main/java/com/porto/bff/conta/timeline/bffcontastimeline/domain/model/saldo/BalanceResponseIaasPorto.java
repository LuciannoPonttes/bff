package com.porto.bff.conta.timeline.bffcontastimeline.domain.model.saldo;

public record BalanceResponseIaasPorto(
        String accountId,
        Double available,
        Double reserved,
        Double blocked
) {
}
