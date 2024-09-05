package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.account;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.account.data.v2.BankAccount;

public record AccountDataEntityResponseDomain(
        String id,
        BankAccountDomain bankAccount,
        String status,
        String type,
        String createdAt,
        String updatedAt
) {

}