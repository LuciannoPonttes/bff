package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.usecase.summary;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.summary.v2.out.client.CardClient;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.backend.BackendResponseDataDomain;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.summary.SummaryEntityResponseDomain;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.ports.decodertoken.out.DecodificarAccessTokenOutPutPort;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.ports.summary.in.SummaryInputPort;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.common.utils.HeaderValidation;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.common.utils.v2.HttpUtils;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.summary.v2.out.response.PortoCardResponse;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.account.v2.client.AccountManagementClient;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.pix.v2.client.PixManagementV2Client;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.config.LogConfig;
import com.porto.experiencia.cliente.conta.digital.commons.domain.exception.BusinessException;
import com.porto.experiencia.cliente.conta.digital.commons.domain.exception.FeignClientException;
import org.springframework.util.CollectionUtils;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import static com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.conta.ContaIassPortoAdapterImpl.buildMessagePixKeys;


public class SummaryUserCase implements SummaryInputPort {

    private final AccountManagementClient client;
    private final CardClient cardPortoClient;
    private final PixManagementV2Client pixKeysClient;
    private final DecodificarAccessTokenOutPutPort tokenDecoder;
    private final HeaderValidation headerValidation;

    public SummaryUserCase(AccountManagementClient client, CardClient cardPortoClient, PixManagementV2Client pixKeysClient, DecodificarAccessTokenOutPutPort tokenDecoder, HeaderValidation headerValidation) {
        this.client = client;
        this.cardPortoClient = cardPortoClient;
        this.pixKeysClient = pixKeysClient;
        this.tokenDecoder = tokenDecoder;
        this.headerValidation = headerValidation;
    }

    @Override
    public BackendResponseDataDomain<SummaryEntityResponseDomain> getSummaryAccount(String cognitoToken, String xItauAuth, String accountId)  {
        headerValidation.isValidHeaderProjet(xItauAuth,accountId);

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
