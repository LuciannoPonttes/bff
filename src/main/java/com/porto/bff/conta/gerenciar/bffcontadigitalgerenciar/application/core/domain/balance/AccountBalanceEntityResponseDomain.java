package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.balance;

public record AccountBalanceEntityResponseDomain (
        Double available,
        Double reserved,
        Double blocked
) {
}