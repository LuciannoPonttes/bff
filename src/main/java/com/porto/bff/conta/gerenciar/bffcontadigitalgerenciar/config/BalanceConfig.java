package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.config;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.usecase.balance.BalanceUseCase;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.ports.balance.in.BalanceInputPort;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.ports.balance.out.DataBalanceClientOutPort;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.common.utils.HeaderValidation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BalanceConfig {

    @Bean
    public BalanceInputPort balanceUseCase(DataBalanceClientOutPort client, HeaderValidation headerValidation) {
        return new BalanceUseCase(client, headerValidation);
    }

}