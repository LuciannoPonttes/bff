package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.ports.account.in;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.account.AccountDataEntityResponseDomain;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.backend.BackendResponseDataDomain;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.balance.BalanceEntityResponseDomain;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.summary.SummaryEntityResponseDomain;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.BackendResponseData;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.account.data.v2.AccountDataEntityResponse;

public interface AccountDataInputPort {

    BackendResponseDataDomain<AccountDataEntityResponseDomain> getAccountData(String xItauAuth, String accountId);
    BackendResponseDataDomain<BalanceEntityResponseDomain> getBalanceAccount(String xItauAuth, String accountId);
    BackendResponseDataDomain<SummaryEntityResponseDomain> getSummaryAccount(String cognitoToken, String xItauAuth, String accountId);

}
