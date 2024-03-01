package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.saldo.v2;

public record AccountBalanceEntityResponse(
        Double available,
        Double reserved,
        Double blocked
) {
}
