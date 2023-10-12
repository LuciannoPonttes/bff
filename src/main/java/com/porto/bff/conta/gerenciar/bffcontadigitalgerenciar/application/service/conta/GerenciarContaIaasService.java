package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.service.conta;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.DataResponseIassPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.conta.AccountResponseIaasPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.saldo.BalanceResponseIaasPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.sumario.SumarioResponseIaasPorto;

public interface GerenciarContaIaasService {
    DataResponseIassPorto<AccountResponseIaasPorto> getConta(String xItauAuth, String contaId);

    DataResponseIassPorto<BalanceResponseIaasPorto> getContaSaldo(String xItauAuth, String contaId);

    DataResponseIassPorto<SumarioResponseIaasPorto> contaSumario(String tokenCognito, String xItauAuth, String contaId);
}
