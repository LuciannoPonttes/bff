package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.porto;

public record BankAccountResponseIassPorto(
        String bank,
        String branch,
        String number,
        String checkDigit
) {
}
