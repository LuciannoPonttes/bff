package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.conta;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.DataResponseIassPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.conta.AccountResponseIaasPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.saldo.BalanceResponseIaasPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.sumario.SumarioResponseIaasPorto;

public interface ContaIassPortoAdapter {

    DataResponseIassPorto<AccountResponseIaasPorto> getConta(String xItauAuth, String contaId);

    DataResponseIassPorto<BalanceResponseIaasPorto> getContaSaldo(String xItauAuth, String contaId);

    DataResponseIassPorto<SumarioResponseIaasPorto> sumarioConta(String tokenCognito, String xItauAuth, String contaId);
}
