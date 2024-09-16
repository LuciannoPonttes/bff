package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.ports.account.out;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.account.AccountDataEntityResponseDomain;

public interface DataAccountClientOutPort {
    AccountDataEntityResponseDomain getAccountData(String xItauAuth, String xAccountProvider, String xAccountId, String accountId);
}