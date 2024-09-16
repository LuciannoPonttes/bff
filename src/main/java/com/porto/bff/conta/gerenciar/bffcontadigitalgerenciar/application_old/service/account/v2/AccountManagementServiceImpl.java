package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application_old.service.account.v2;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountManagementServiceImpl {
   /*
    private final AccountManagementAdapter adapter;
    private final HeaderValidation headerValidation;

    @Override
    public BackendResponseData<AccountBalanceEntityResponse> getBalanceAccount(String xItauAuth, String accountId) {
        headerValidation.isValidHeaderProjet(xItauAuth,accountId);
        return this.adapter.getBalanceAccount(xItauAuth, accountId);
    }

    @Override
    public BackendResponseData<AccountDataEntityResponse> getAccountData(String xItauAuth, String accountId) {
        headerValidation.isValidHeaderProjet(xItauAuth,accountId);
        return this.adapter.getAccountData(xItauAuth, accountId);
    }

    @Override
    public BackendResponseData<AccountSummaryEntityResponse> getSummaryAccount(String cognitoToken, String xItauAuth, String accountId) {
        headerValidation.isValidHeaderProjet(xItauAuth,accountId);
        return this.adapter.getSummaryAccount(cognitoToken, xItauAuth, accountId);
    }*/
}
