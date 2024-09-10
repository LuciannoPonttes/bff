package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.usecase.balance;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.balance.v2.out.client.DataBalanceClient;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.backend.BackendResponseDataDomain;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.balance.BalanceEntityResponseDomain;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.ports.balance.in.BalanceInputPort;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.common.utils.HeaderValidation;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.common.utils.v2.HttpUtils;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.BackendResponseData;
import com.porto.experiencia.cliente.conta.digital.commons.domain.exception.BusinessException;
import com.porto.experiencia.cliente.conta.digital.commons.domain.exception.FeignClientException;
import lombok.RequiredArgsConstructor;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BalanceUserCase implements BalanceInputPort {

    private final DataBalanceClient client;
    private final HeaderValidation headerValidation;

    @Override
    @Retryable(retryFor = { BusinessException.class }, backoff = @Backoff(delay = 150))
    public BackendResponseDataDomain<BalanceEntityResponseDomain> getBalanceAccount(String xItauAuth, String accountId) {
        headerValidation.isValidHeaderProjet(xItauAuth,accountId);
        try {

            return new BackendResponseDataDomain<>(this.client.getBalanceAccount(
                    HttpUtils.includeBearerTokenPrefix(xItauAuth),
                    HttpUtils.HTTP_PROVIDER_VALUE,
                    accountId,
                    accountId
            ));
        } catch (FeignClientException e) {
            throw new BusinessException(Integer.valueOf(e.getCodigo()), "BALANCE_ACCOUNT_ERROR", e.getMessage());
        }
    }
}
