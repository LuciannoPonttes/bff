package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.summary;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.account.AccountDataEntityResponseDomain;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.balance.BalanceEntityResponseDomain;

public record SummaryEntityResponseDomain (
        String document,
        AccountDataEntityResponseDomain account,
        BalanceEntityResponseDomain balance,
        boolean hasPortoCard,
        String pixKeyCount
) {

}