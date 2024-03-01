package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.account.v2;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.BackendResponseData;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.saldo.v2.AccountBalanceEntityResponse;

public interface AccountManagementAdapter {
    BackendResponseData<AccountBalanceEntityResponse> getBalanceAccount(String xItauAuth, String accountId);
}
