package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.usecase.account;


import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.account.AccountDataEntityResponseDomain;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.backend.BackendResponseDataDomain;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.common.utils.HeaderValidation;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.ports.account.out.DataAccountClientOutPort;



public class AccountDataUseCase {
/*
    private final DataAccountClientOutPort client;
    private final HeaderValidation headerValidation;

    public AccountDataUseCase(DataAccountClientOutPort client, HeaderValidation headerValidation) {
        this.client = client;
        this.headerValidation = headerValidation;
    }


    public BackendResponseDataDomain<AccountDataEntityResponseDomain> getAccountData(String xItauAuth, String accountId) {
        headerValidation.isValidHeaderProjet(xItauAuth, accountId);
        AccountDataEntityResponseDomain accountData = this.client.getAccountData(
                xItauAuth,
                "ITAU",
                accountId,
                accountId,
                HttpUtils.HTTP_ACCOUNT_FIELDS_VALUE);
        return new BackendResponseDataDomain<>(accountData);
    }*/
}
