package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.sumario;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.DataResponseIassPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.conta.AccountResponseIaasPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.saldo.BalanceResponseIaasPorto;

public record SumarioResponseIaasPorto(
        String document,
        DataResponseIassPorto<AccountResponseIaasPorto> account,
        DataResponseIassPorto<BalanceResponseIaasPorto> balance,
        boolean hasPortoCard,
        String quantidadeChavePix
) {
}
