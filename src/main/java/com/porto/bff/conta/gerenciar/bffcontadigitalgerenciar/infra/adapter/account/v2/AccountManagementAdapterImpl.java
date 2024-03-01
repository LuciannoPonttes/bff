package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.account.v2;


import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.common.utils.v2.HttpUtils;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.BackendResponseData;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.saldo.v2.AccountBalanceEntityResponse;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.account.v2.client.AccountManagementClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountManagementAdapterImpl implements AccountManagementAdapter {
    private final AccountManagementClient client;
    @Override
    public BackendResponseData<AccountBalanceEntityResponse> getBalanceAccount(String xItauAuth, String accountId) {
        return this.client.getBalanceAccount(HttpUtils.includeBearerTokenPrefix(xItauAuth), HttpUtils.HTTP_PROVIDER_VALUE, accountId, accountId);
    }
}
