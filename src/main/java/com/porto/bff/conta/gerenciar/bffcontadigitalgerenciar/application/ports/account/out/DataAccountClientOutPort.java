package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.ports.account.out;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.account.AccountDataEntityResponseDomain;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.balance.BalanceEntityResponseDomain;

public interface DataAccountClientOutPort {
    AccountDataEntityResponseDomain getAccountData(String xItauAuth, String xAccountProvider, String xAccountId, String accountId, String httpAccountFieldsValue);

    BalanceEntityResponseDomain getBalanceAccount(String xItauAuth,
                                                  String xAccountProvider,
                                                  String xAccountId,
                                                  String accountId);
}