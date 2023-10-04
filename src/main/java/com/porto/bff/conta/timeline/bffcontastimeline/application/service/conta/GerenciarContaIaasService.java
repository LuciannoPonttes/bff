package com.porto.bff.conta.timeline.bffcontastimeline.application.service.conta;

import com.porto.bff.conta.timeline.bffcontastimeline.domain.model.DataResponseIassPorto;
import com.porto.bff.conta.timeline.bffcontastimeline.domain.model.conta.AccountResponseIaasPorto;
import com.porto.bff.conta.timeline.bffcontastimeline.domain.model.saldo.BalanceResponseIaasPorto;
import com.porto.bff.conta.timeline.bffcontastimeline.domain.model.sumario.SumarioResponseIaasPorto;

public interface GerenciarContaIaasService {
    DataResponseIassPorto<AccountResponseIaasPorto> getConta(String xItauAuth, String contaId);

    DataResponseIassPorto<BalanceResponseIaasPorto> getContaSaldo(String xItauAuth, String contaId);

    DataResponseIassPorto<SumarioResponseIaasPorto> contaSumario(String tokenCognito, String xItauAuth, String contaId);
}
