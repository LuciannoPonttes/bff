package com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.conta;

import com.porto.bff.conta.timeline.bffcontastimeline.domain.model.DataResponseIassPorto;
import com.porto.bff.conta.timeline.bffcontastimeline.domain.model.conta.AccountResponseIaasPorto;
import com.porto.bff.conta.timeline.bffcontastimeline.domain.model.saldo.BalanceResponseIaasPorto;
import com.porto.bff.conta.timeline.bffcontastimeline.domain.model.sumario.SumarioResponseIaasPorto;

public interface ContaIassPortoAdapter {

    DataResponseIassPorto<AccountResponseIaasPorto> getConta(String xItauAuth, String contaId);

    DataResponseIassPorto<BalanceResponseIaasPorto> getContaSaldo(String xItauAuth, String contaId);

    DataResponseIassPorto<SumarioResponseIaasPorto> sumarioConta(String tokenCognito, String xItauAuth, String contaId);
}
