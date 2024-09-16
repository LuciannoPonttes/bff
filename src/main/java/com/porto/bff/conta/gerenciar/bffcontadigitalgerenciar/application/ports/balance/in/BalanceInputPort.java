package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.ports.balance.in;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.backend.BackendResponseDataDomain;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.balance.BalanceEntityResponseDomain;
import org.springframework.stereotype.Component;


public interface BalanceInputPort {

    BackendResponseDataDomain<BalanceEntityResponseDomain> getBalanceAccount(String xItauAuth, String accountId);
}
