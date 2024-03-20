package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.account.data.v2;

public record BankAccount(
        String company,
        String bank,
        String branch,
        String number,
        String checkDigit,
        String type
) {
}
