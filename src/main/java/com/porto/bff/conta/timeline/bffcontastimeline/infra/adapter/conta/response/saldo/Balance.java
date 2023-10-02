package com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.conta.response.saldo;

public record Balance(
        String accounId,
        Double available,
        Double reserved,
        Double blocked
) {
}
