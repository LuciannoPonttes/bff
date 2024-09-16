package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.ports.balance.out;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.backend.BackendResponseDataDomain;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.balance.BalanceEntityResponseDomain;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.common.utils.v2.HttpUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

public interface DataBalanceClientOutPort {
    BalanceEntityResponseDomain getBalanceAccount(String xItauAuth,
                                                  String xAccountProvider,
                                                  String xAccountId,
                                                  String accountId);
}
