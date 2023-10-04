package com.porto.bff.conta.timeline.bffcontastimeline.domain.model.sumario;

import com.porto.bff.conta.timeline.bffcontastimeline.domain.model.conta.AccountResponseIaasPorto;
import com.porto.bff.conta.timeline.bffcontastimeline.domain.model.saldo.BalanceResponseIaasPorto;

public record SumarioResponseIaasPorto(
        AccountResponseIaasPorto account,
        BalanceResponseIaasPorto balance
) {
}
