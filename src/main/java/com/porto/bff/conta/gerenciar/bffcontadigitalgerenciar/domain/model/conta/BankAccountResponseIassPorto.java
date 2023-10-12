package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.conta;

public record BankAccountResponseIassPorto(
        String bank,
        String branch,
        String number,
        String checkDigit
) {
}
