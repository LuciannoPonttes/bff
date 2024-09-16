package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.account.v2.out.client;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.account.AccountDataEntityResponseDomain;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.ports.account.out.DataAccountClientOutPort;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.common.utils.v2.HttpUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataAccountAdapter implements DataAccountClientOutPort {

    private final DataAccountClient client;

    @Override
    public AccountDataEntityResponseDomain getAccountData(String xItauAuth, String xAccountProvider, String xAccountId, String accountId) {
        return this.client.getAccountData(
                HttpUtils.includeBearerTokenPrefix(xItauAuth),
                HttpUtils.HTTP_PROVIDER_VALUE,
                xAccountId,
                accountId,
                HttpUtils.HTTP_ACCOUNT_FIELDS_VALUE
        );
    }
}