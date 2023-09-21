package com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.conta.response;

public record ContaSaldoResponseIaasPorto(
         String accountId,
         double available,
         double reserved,
         double blocked
) {
}
