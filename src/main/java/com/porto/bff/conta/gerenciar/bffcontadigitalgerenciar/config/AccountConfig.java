package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.config;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.summary.v2.out.client.CardClient;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.usecase.AccountManagementUserCase;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.ports.account.in.AccountDataInputPort;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.ports.account.out.DataAccountClientOutPort;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.ports.balance.out.DataBalanceClientOutPort;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.ports.decodertoken.out.DecodificarAccessTokenOutPutPort;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.common.utils.HeaderValidation;
//import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.account.v2.client.AccountManagementClient;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.porto.client.PixManagementV2Client;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountConfig {

    @Bean
    public AccountDataInputPort accountDataUseCase(DataAccountClientOutPort accountClient,
                                                   HeaderValidation headerValidation,
                                                   DataBalanceClientOutPort balanceClient,
                                                   DataAccountClientOutPort clientSummary,
                                                   CardClient cardPortoClient,
                                                   PixManagementV2Client pixKeysClient,
                                                   DecodificarAccessTokenOutPutPort tokenDecoder) {
        return new AccountManagementUserCase(balanceClient, headerValidation, accountClient, clientSummary, cardPortoClient, pixKeysClient, tokenDecoder);
    }






}
