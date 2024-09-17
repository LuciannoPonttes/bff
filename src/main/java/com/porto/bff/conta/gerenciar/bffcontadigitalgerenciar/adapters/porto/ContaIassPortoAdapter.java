package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.porto;


import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.porto.AccountResponseIaasPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.porto.BalanceResponseIaasPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.porto.DataResponseIassPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.porto.SumarioResponseIaasPorto;

public interface ContaIassPortoAdapter {

    DataResponseIassPorto<AccountResponseIaasPorto> getConta(String xItauAuth, String contaId);

    DataResponseIassPorto<BalanceResponseIaasPorto> getContaSaldo(String xItauAuth, String contaId);

    DataResponseIassPorto<SumarioResponseIaasPorto> sumarioConta(String tokenCognito, String xItauAuth, String contaId);
}
