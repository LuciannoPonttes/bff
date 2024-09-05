package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.account;

public record BankAccountDomain(
        String company,
        String bank,
        String branch,
        String number,
        String checkDigit,
        String type
) {
}