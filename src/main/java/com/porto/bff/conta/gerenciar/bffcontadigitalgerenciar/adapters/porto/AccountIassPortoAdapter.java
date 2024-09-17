package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.porto;


import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.porto.AccountResponseIaasPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.porto.BalanceResponseIaasPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.porto.DataResponseIassPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.porto.SummaryResponseIaasPorto;

public interface AccountIassPortoAdapter {

    DataResponseIassPorto<AccountResponseIaasPorto> getAccount(String xItauAuth, String contaId);

    DataResponseIassPorto<BalanceResponseIaasPorto> getBalance(String xItauAuth, String contaId);

    DataResponseIassPorto<SummaryResponseIaasPorto> getSummary(String tokenCognito, String xItauAuth, String contaId);
}
