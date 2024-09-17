package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.account;


public record AccountDataEntityResponse(
        String id,
        BankAccount bankAccount,
        String status,
        String type,
        String createdAt,
        String updatedAt
) {

}
