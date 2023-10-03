package com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.conta.response.saldo;

public record Balance(
        String accountId,
        Double available,
        Double reserved,
        Double blocked
) {
}
