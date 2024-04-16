package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.account.v2;


import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.common.utils.v2.HttpUtils;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.BackendResponseData;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.account.balance.v2.AccountBalanceEntityResponse;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.account.data.v2.AccountDataEntityResponse;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.account.sumary.v2.AccountSummaryEntityResponse;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.cartoes.ListaCartoesResponse;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.account.v2.client.AccountManagementClient;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.conta.client.CartoesPortoClient;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.decodertoken.DecodificarAccessToken;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import static org.apache.commons.lang3.StringUtils.EMPTY;

@Component
@RequiredArgsConstructor
public class AccountManagementAdapterImpl implements AccountManagementAdapter {
    private final AccountManagementClient client;
    private final CartoesPortoClient cardPortoClient;
    private final DecodificarAccessToken tokenDecoder;

    @Override
    public BackendResponseData<AccountBalanceEntityResponse> getBalanceAccount(String xItauAuth, String accountId) {
        return this.client.getBalanceAccount(HttpUtils.includeBearerTokenPrefix(xItauAuth), HttpUtils.HTTP_PROVIDER_VALUE, accountId, accountId);
    }

    @Override
    public BackendResponseData<AccountDataEntityResponse> getAccountData(String xItauAuth, String accountId) {
        return this.client.getAccountData(HttpUtils.includeBearerTokenPrefix(xItauAuth), HttpUtils.HTTP_PROVIDER_VALUE,
                accountId, accountId, HttpUtils.HTTP_ACCOUNT_FIELDS_VALUE);
    }

    @SneakyThrows
    @Override
    public BackendResponseData<AccountSummaryEntityResponse> getSummaryAccount(String cognitoToken, String xItauAuth, String accountId) {
        var document = this.tokenDecoder.getCpfPorToken(cognitoToken);
        var balanceResponseFuture = CompletableFuture.supplyAsync(() ->
                this.client.getBalanceAccount(HttpUtils.includeBearerTokenPrefix(xItauAuth), HttpUtils.HTTP_PROVIDER_VALUE, accountId, accountId));
        var accountResponseFuture = CompletableFuture.supplyAsync(() ->
                this.client.getAccountData(HttpUtils.includeBearerTokenPrefix(xItauAuth), HttpUtils.HTTP_PROVIDER_VALUE,
                        accountId, accountId, HttpUtils.HTTP_ACCOUNT_FIELDS_VALUE));
        var portoCardResponseFuture = CompletableFuture.supplyAsync(() -> this.cardPortoClient.getCardsByuser(cognitoToken))
                .exceptionally(throwable -> new ListaCartoesResponse());
        try {
            CompletableFuture.allOf(accountResponseFuture, balanceResponseFuture, portoCardResponseFuture).join();
            var hasPortoCard = Objects.nonNull(portoCardResponseFuture.join())
                    && Objects.nonNull(portoCardResponseFuture.join().getDados())
                    && !CollectionUtils.isEmpty(portoCardResponseFuture.join().getDados().getLista());
            return new BackendResponseData<>(new AccountSummaryEntityResponse(document, accountResponseFuture.join().data(), balanceResponseFuture.join().data(),
                    hasPortoCard, EMPTY));
        } catch (CompletionException exception) {
            throw exception.getCause();
        }
    }
}
