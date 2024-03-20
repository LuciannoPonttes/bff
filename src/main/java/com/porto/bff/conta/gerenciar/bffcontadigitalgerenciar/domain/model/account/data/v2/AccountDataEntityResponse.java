package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.account.data.v2;


public record AccountDataEntityResponse(
        String id,
        BankAccount bankAccount,
        String status,
        String type,
        String createdAt,
        String updatedAt
) {

}
