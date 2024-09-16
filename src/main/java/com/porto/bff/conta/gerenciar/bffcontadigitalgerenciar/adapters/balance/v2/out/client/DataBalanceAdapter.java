package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.balance.v2.out.client;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.backend.BackendResponseDataDomain;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.balance.BalanceEntityResponseDomain;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.ports.balance.out.DataBalanceClientOutPort;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.common.utils.v2.HttpUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataBalanceAdapter implements DataBalanceClientOutPort {
    private final DataBalanceClient client;

    @Override
    public BalanceEntityResponseDomain getBalanceAccount(String xItauAuth, String xAccountProvider, String xAccountId, String accountId) {
        return new BackendResponseDataDomain<>(this.client.getBalanceAccount(
                HttpUtils.includeBearerTokenPrefix(xItauAuth),
                HttpUtils.HTTP_PROVIDER_VALUE,
                accountId,
                accountId
        )).data();
    }
}
