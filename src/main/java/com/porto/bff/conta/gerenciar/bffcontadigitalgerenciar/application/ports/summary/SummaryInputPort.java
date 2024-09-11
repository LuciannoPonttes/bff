package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.ports.summary;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.backend.BackendResponseDataDomain;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.summary.SummaryEntityResponseDomain;

public interface SummaryInputPort {

    BackendResponseDataDomain<SummaryEntityResponseDomain> getSummaryAccount(String cognitoToken, String xItauAuth, String accountId);
}
