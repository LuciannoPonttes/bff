package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.account.balance.v2;

public record AccountBalanceEntityResponse(
        Double available,
        Double reserved,
        Double blocked
) {
}
