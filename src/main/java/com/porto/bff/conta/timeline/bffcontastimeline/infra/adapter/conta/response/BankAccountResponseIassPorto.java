package com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.conta.response;

public record BankAccountResponseIassPorto(
        String bank,
        String branch,
        String number,
        String checkDigit
) {
}
