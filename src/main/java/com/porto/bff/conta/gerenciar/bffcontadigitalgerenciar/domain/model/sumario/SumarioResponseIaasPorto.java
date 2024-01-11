package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.sumario;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.DataResponseIassPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.conta.AccountResponseIaasPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.saldo.BalanceResponseIaasPorto;
import lombok.With;

public record SumarioResponseIaasPorto(
        String document,
        @With
        DataResponseIassPorto<AccountResponseIaasPorto> account,
        DataResponseIassPorto<BalanceResponseIaasPorto> balance,
        boolean hasPortoCard,
        String quantidadeChavePix
) {
}
