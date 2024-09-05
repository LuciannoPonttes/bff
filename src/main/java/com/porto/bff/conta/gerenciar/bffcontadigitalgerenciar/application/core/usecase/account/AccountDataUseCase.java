package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.usecase.account;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.account.v2.out.client.DataAccountClient;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.ports.account.in.AccountDataInputPort;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.common.utils.HeaderValidation;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.common.utils.v2.HttpUtils;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.BackendResponseData;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.account.data.v2.AccountDataEntityResponse;
import com.porto.experiencia.cliente.conta.digital.commons.domain.exception.BusinessException;
import com.porto.experiencia.cliente.conta.digital.commons.domain.exception.FeignClientException;
import lombok.RequiredArgsConstructor;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class AccountDataUseCase implements AccountDataInputPort {

    private final DataAccountClient dataAccountClient;
    private final HeaderValidation headerValidation;

    @Override
    @Retryable(retryFor = { BusinessException.class }, backoff = @Backoff(delay = 150))
    public BackendResponseData<AccountDataEntityResponse> getAccountData(String xItauAuth, String accountId) {
        headerValidation.isValidHeaderProjet(xItauAuth,accountId);
        try {
            return new BackendResponseData<>(this.dataAccountClient.getAccountData(
                    HttpUtils.includeBearerTokenPrefix(xItauAuth),
                    HttpUtils.HTTP_PROVIDER_VALUE,
                    accountId,
                    accountId,
                    HttpUtils.HTTP_ACCOUNT_FIELDS_VALUE
            ));
        } catch (FeignClientException e) {
            throw new BusinessException(Integer.valueOf(e.getCodigo()), "ACCOUNT_DATA_ERROR", e.getMessage());
        }
    }
}
