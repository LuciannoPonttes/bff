package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.service.account.v2;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.BackendResponseData;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.account.balance.v2.AccountBalanceEntityResponse;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.account.data.v2.AccountDataEntityResponse;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.account.v2.AccountManagementAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountManagementServiceImpl implements AccountManagementService {
    private final AccountManagementAdapter adapter;

    @Override
    public BackendResponseData<AccountBalanceEntityResponse> getBalanceAccount(String xItauAuth, String accountId) {
        return this.adapter.getBalanceAccount(xItauAuth, accountId);
    }

    @Override
    public BackendResponseData<AccountDataEntityResponse> getAccountData(String xItauAuth, String accountId) {
        return this.adapter.getAccountData(xItauAuth, accountId);
    }
}
