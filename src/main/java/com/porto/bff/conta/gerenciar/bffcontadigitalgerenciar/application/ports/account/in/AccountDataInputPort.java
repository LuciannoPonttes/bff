package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.ports.account.in;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.BackendResponseData;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.account.data.v2.AccountDataEntityResponse;

public interface AccountDataInputPort {

    BackendResponseData<AccountDataEntityResponse> getAccountData(String xItauAuth, String accountId);

}
