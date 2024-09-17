package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.porto;


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
