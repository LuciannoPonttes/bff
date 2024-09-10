package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.balance;

public record BalanceEntityResponseDomain(
        Double available,
        Double reserved,
        Double blocked
) {
}