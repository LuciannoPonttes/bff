package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application_old.service.account.v2;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.BackendResponseData;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.account.balance.v2.AccountBalanceEntityResponse;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.account.data.v2.AccountDataEntityResponse;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.account.sumary.v2.AccountSummaryEntityResponse;

public interface AccountManagementService {

    BackendResponseData<AccountBalanceEntityResponse> getBalanceAccount(String xItauAuth, String accountId);

    BackendResponseData<AccountDataEntityResponse> getAccountData(String xItauAuth, String accountId);

    BackendResponseData<AccountSummaryEntityResponse> getSummaryAccount(String cognitoToken, String xItauAuth, String accountId);
}
