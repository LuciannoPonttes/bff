package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.account;
public record AccountDataEntityResponseDomain(
        String id,
        BankAccountDomain bankAccount,
        String status,
        String type,
        String createdAt,
        String updatedAt
) {

}