package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.summary;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.account.balance.v2.AccountBalanceEntityResponse;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.account.data.v2.AccountDataEntityResponse;

public record SummaryEntityResponseDomain (
        String document,
        AccountDataEntityResponse account,
        AccountBalanceEntityResponse balance,
        boolean hasPortoCard,
        String pixKeyCount
) {
}