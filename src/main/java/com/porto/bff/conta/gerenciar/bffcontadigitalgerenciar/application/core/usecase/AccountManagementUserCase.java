package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.usecase;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.summary.v2.out.client.CardClient;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.summary.v2.out.response.PortoCardResponse;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.account.AccountDataEntityResponseDomain;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.backend.BackendResponseDataDomain;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.balance.BalanceEntityResponseDomain;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.summary.SummaryEntityResponseDomain;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.ports.account.in.AccountDataInputPort;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.ports.account.out.DataAccountClientOutPort;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.ports.balance.out.DataBalanceClientOutPort;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.ports.decodertoken.out.DecodificarAccessTokenOutPutPort;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.common.utils.HeaderValidation;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.common.utils.v2.HttpUtils;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.porto.client.PixManagementV2Client;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.config.LogConfig;
import com.porto.experiencia.cliente.conta.digital.commons.domain.exception.BusinessException;
import com.porto.experiencia.cliente.conta.digital.commons.domain.exception.FeignClientException;
import org.springframework.util.CollectionUtils;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import static com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.porto.ContaIassPortoAdapterImpl.buildMessagePixKeys;

public class AccountManagementUserCase implements AccountDataInputPort {

    private final DataBalanceClientOutPort clientBalance;
    private final HeaderValidation headerValidation;
    private final DataAccountClientOutPort client;
    private final DataAccountClientOutPort clientSummary;
    private final CardClient cardPortoClient;
    private final PixManagementV2Client pixKeysClient;
    private final DecodificarAccessTokenOutPutPort tokenDecoder;

    public AccountManagementUserCase(DataBalanceClientOutPort clientBalance, HeaderValidation headerValidation, DataAccountClientOutPort client, DataAccountClientOutPort clientSummary, CardClient cardPortoClient, PixManagementV2Client pixKeysClient, DecodificarAccessTokenOutPutPort tokenDecoder) {
        this.clientBalance = clientBalance;
        this.headerValidation = headerValidation;
        this.client = client;
        this.clientSummary = clientSummary;
        this.cardPortoClient = cardPortoClient;
        this.pixKeysClient = pixKeysClient;
        this.tokenDecoder = tokenDecoder;
    }

    @Override
    public BackendResponseDataDomain<AccountDataEntityResponseDomain> getAccountData(String xItauAuth, String accountId) {
        headerValidation.isValidHeaderProjet(xItauAuth, accountId);
        AccountDataEntityResponseDomain accountData = this.client.getAccountData(
                xItauAuth,
                "ITAU",
                accountId,
                accountId,
                HttpUtils.HTTP_ACCOUNT_FIELDS_VALUE);
        return new BackendResponseDataDomain<>(accountData);
    }

    @Override
    public BackendResponseDataDomain<BalanceEntityResponseDomain> getBalanceAccount(String xItauAuth, String accountId) {
        headerValidation.isValidHeaderProjet(xItauAuth,accountId);
        return new BackendResponseDataDomain<>(this.clientBalance.getBalanceAccount(
                HttpUtils.includeBearerTokenPrefix(xItauAuth),
                HttpUtils.HTTP_PROVIDER_VALUE,
                accountId,
                accountId
        ));

    }
    @Override
    public BackendResponseDataDomain<SummaryEntityResponseDomain> getSummaryAccount(String cognitoToken, String xItauAuth, String accountId)  {
        headerValidation.isValidHeaderProjet(xItauAuth,accountId);

        var document = this.tokenDecoder.getCpfPorToken(cognitoToken);

        var balanceResponseFuture = CompletableFuture.supplyAsync(LogConfig.withMdc(() -> {
            try {
                return this.clientSummary.getBalanceAccount(HttpUtils.includeBearerTokenPrefix(xItauAuth), HttpUtils.HTTP_PROVIDER_VALUE, accountId, accountId);
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
                return this.clientSummary.getAccountData(HttpUtils.includeBearerTokenPrefix(xItauAuth), HttpUtils.HTTP_PROVIDER_VALUE,
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

            return new BackendResponseDataDomain<>(new SummaryEntityResponseDomain(
                    document,
                    accountResponseFuture.join(),
                    balanceResponseFuture.join(),
                    hasPortoCard,
                    pixKeyCount
            ));
        } catch (CompletionException exception) {
            try {
                throw exception.getCause();
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
    }
}
