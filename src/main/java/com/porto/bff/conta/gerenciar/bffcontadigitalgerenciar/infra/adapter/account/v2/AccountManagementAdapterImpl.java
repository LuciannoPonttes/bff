package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.account.v2;


import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.common.utils.v2.HttpUtils;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.BackendResponseData;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.account.balance.v2.AccountBalanceEntityResponse;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.account.data.v2.AccountDataEntityResponse;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.account.sumary.v2.AccountSummaryEntityResponse;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.summary.v2.out.response.PortoCardResponse;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.account.v2.client.AccountManagementClient;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.conta.client.PortoCardClient;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.decodertoken.DecodificarAccessToken;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.pix.v2.client.PixManagementV2Client;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.config.LogConfig;
import com.porto.experiencia.cliente.conta.digital.commons.domain.exception.BusinessException;
import com.porto.experiencia.cliente.conta.digital.commons.domain.exception.FeignClientException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import static com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.conta.ContaIassPortoAdapterImpl.buildMessagePixKeys;

@Component
@RequiredArgsConstructor
public class AccountManagementAdapterImpl implements AccountManagementAdapter {

    private final AccountManagementClient client;
    private final PortoCardClient cardPortoClient;
    private final PixManagementV2Client pixKeysClient;
    private final DecodificarAccessToken tokenDecoder;

    @Override
    @Retryable(retryFor = { BusinessException.class }, backoff = @Backoff(delay = 150))
    public BackendResponseData<AccountBalanceEntityResponse> getBalanceAccount(String xItauAuth, String accountId) {
        try {

             return new BackendResponseData<>(this.client.getBalanceAccount(
                    HttpUtils.includeBearerTokenPrefix(xItauAuth),
                    HttpUtils.HTTP_PROVIDER_VALUE,
                    accountId,
                    accountId
            ));
        } catch (FeignClientException e) {
            throw new BusinessException(Integer.valueOf(e.getCodigo()), "BALANCE_ACCOUNT_ERROR", e.getMessage());
        }
    }

    @Override
    @Retryable(retryFor = { BusinessException.class }, backoff = @Backoff(delay = 150))
    public BackendResponseData<AccountDataEntityResponse> getAccountData(String xItauAuth, String accountId) {
        try {
            return new BackendResponseData<>(this.client.getAccountData(
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

    @Override
    @SneakyThrows
    @Retryable(retryFor = { BusinessException.class }, backoff = @Backoff(delay = 150))
    public BackendResponseData<AccountSummaryEntityResponse> getSummaryAccount(String cognitoToken, String xItauAuth, String accountId) {
        var document = this.tokenDecoder.getCpfPorToken(cognitoToken);

        var balanceResponseFuture = CompletableFuture.supplyAsync(LogConfig.withMdc(() -> {
            try {
                return this.client.getBalanceAccount(HttpUtils.includeBearerTokenPrefix(xItauAuth), HttpUtils.HTTP_PROVIDER_VALUE, accountId, accountId);
            } catch (FeignClientException e) {
                throw new BusinessException(Integer.valueOf(e.getCodigo()), "BALANCE_ACCOUNT_ERROR", e.getMessage());
            }
        }));

        var pixKeysResponseFuture = CompletableFuture.supplyAsync(LogConfig.withMdc(() -> {
            try {
                return this.pixKeysClient.getPixKeyFromAnAccount(cognitoToken, HttpUtils.includeBearerTokenPrefix(xItauAuth), accountId);
            } catch (FeignClientException e) {
                throw new BusinessException(Integer.valueOf(e.getCodigo()), "PIX_KEY_ERROR", e.getMessage());
            }
        }));

        var accountResponseFuture = CompletableFuture.supplyAsync(LogConfig.withMdc(() -> {
            try {
                return this.client.getAccountData(HttpUtils.includeBearerTokenPrefix(xItauAuth), HttpUtils.HTTP_PROVIDER_VALUE,
                        accountId, accountId, HttpUtils.HTTP_ACCOUNT_FIELDS_VALUE);
            } catch (FeignClientException e) {
                throw new BusinessException(Integer.valueOf(e.getCodigo()), "ACCOUNT_DATA_ERROR", e.getMessage());
            }
        }));

        var portoCardResponseFuture = CompletableFuture.supplyAsync(LogConfig.withMdc(() -> {
            try {
                return this.cardPortoClient.getCardsByuser(cognitoToken);
            } catch (Exception e) {
                return new PortoCardResponse();
            }
        }));

        try {
            CompletableFuture.allOf(accountResponseFuture, balanceResponseFuture, portoCardResponseFuture).join();

            var hasPortoCard = Objects.nonNull(portoCardResponseFuture.join())
                    && Objects.nonNull(portoCardResponseFuture.join().getDados())
                    && !CollectionUtils.isEmpty(portoCardResponseFuture.join().getDados().getLista());

            var pixKeyCount = buildMessagePixKeys(pixKeysResponseFuture.join().dados().size());

            return new BackendResponseData<>(new AccountSummaryEntityResponse(
                    document,
                    accountResponseFuture.join(),
                    balanceResponseFuture.join(),
                    hasPortoCard,
                    pixKeyCount
            ));
        } catch (CompletionException exception) {
            throw exception.getCause();
        }
    }
}
