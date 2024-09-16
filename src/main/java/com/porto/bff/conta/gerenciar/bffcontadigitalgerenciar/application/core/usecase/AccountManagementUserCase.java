package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.usecase;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.account.AccountDataEntityResponseDomain;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.backend.BackendResponseDataDomain;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.balance.BalanceEntityResponseDomain;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.ports.account.in.AccountDataInputPort;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.ports.account.out.DataAccountClientOutPort;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.ports.balance.out.DataBalanceClientOutPort;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.common.utils.HeaderValidation;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.common.utils.v2.HttpUtils;

public class AccountManagementUserCase implements AccountDataInputPort {

    private final DataBalanceClientOutPort clientBalance;
    private final HeaderValidation headerValidation;
    private final DataAccountClientOutPort client;

    public AccountManagementUserCase(DataBalanceClientOutPort clientBalance, HeaderValidation headerValidation, DataAccountClientOutPort client) {
        this.clientBalance = clientBalance;
        this.headerValidation = headerValidation;
        this.client = client;
    }

    @Override
    public BackendResponseDataDomain<AccountDataEntityResponseDomain> getAccountData(String xItauAuth, String accountId) {
        headerValidation.isValidHeaderProjet(xItauAuth, accountId);
        AccountDataEntityResponseDomain accountData = this.client.getAccountData(
                xItauAuth,
                "ITAU",
                accountId,
                accountId
        );
        return new BackendResponseDataDomain<>(accountData);
    }

    @Override
    public BackendResponseDataDomain<BalanceEntityResponseDomain> getBalanceAccount(String xItauAuth, String accountId) {
        headerValidation.isValidHeaderProjet(xItauAuth,accountId);
        return new BackendResponseDataDomain<>(this.clientBalance.getBalanceAccount(
                HttpUtils.includeBearerTokenPrefix(xItauAuth),
                HttpUtils.HTTP_PROVIDER_VALUE,
                accountId,
                accountId
        ));

    }
}
