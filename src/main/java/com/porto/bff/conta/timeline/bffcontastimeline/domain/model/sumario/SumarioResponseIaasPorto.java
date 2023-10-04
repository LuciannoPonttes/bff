package com.porto.bff.conta.timeline.bffcontastimeline.domain.model.sumario;

import com.porto.bff.conta.timeline.bffcontastimeline.domain.model.DataResponseIassPorto;
import com.porto.bff.conta.timeline.bffcontastimeline.domain.model.conta.AccountResponseIaasPorto;
import com.porto.bff.conta.timeline.bffcontastimeline.domain.model.saldo.BalanceResponseIaasPorto;

public record SumarioResponseIaasPorto(
        String document,
        DataResponseIassPorto<AccountResponseIaasPorto> account,
        DataResponseIassPorto<BalanceResponseIaasPorto> balance
) {
}
