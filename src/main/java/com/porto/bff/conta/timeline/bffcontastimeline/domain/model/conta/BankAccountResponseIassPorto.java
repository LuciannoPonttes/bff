package com.porto.bff.conta.timeline.bffcontastimeline.domain.model.conta;

public record BankAccountResponseIassPorto(
        String bank,
        String branch,
        String number,
        String checkDigit
) {
}
