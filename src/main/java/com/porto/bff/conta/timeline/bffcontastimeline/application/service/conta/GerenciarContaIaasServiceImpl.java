package com.porto.bff.conta.timeline.bffcontastimeline.application.service.conta;

import com.porto.bff.conta.timeline.bffcontastimeline.domain.model.DataResponseIassPorto;
import com.porto.bff.conta.timeline.bffcontastimeline.domain.model.conta.AccountResponseIaasPorto;
import com.porto.bff.conta.timeline.bffcontastimeline.domain.model.saldo.BalanceResponseIaasPorto;
import com.porto.bff.conta.timeline.bffcontastimeline.domain.model.sumario.SumarioResponseIaasPorto;
import com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.conta.ContaIassPortoAdapter;
import com.porto.experiencia.cliente.conta.digital.commons.domain.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class GerenciarContaIaasServiceImpl implements GerenciarContaIaasService {

    private final ContaIassPortoAdapter adapter;

    @Override
    public DataResponseIassPorto<AccountResponseIaasPorto> getConta(String xItauAuth, String contaId) {
        isValidHeader(xItauAuth, contaId);
        return this.adapter.getConta(xItauAuth, contaId);
    }

    @Override
    public DataResponseIassPorto<BalanceResponseIaasPorto> getContaSaldo(String xItauAuth, String contaId) {
        isValidHeader(xItauAuth, contaId);
        return this.adapter.getContaSaldo(xItauAuth, contaId);
    }

    @Override
    public DataResponseIassPorto<SumarioResponseIaasPorto> contaSumario(String tokenCognito, String xItauAuth, String contaId) {
        isValidHeader(xItauAuth, contaId);
        return adapter.sumarioConta(tokenCognito, xItauAuth, contaId);
    }

    private void isValidHeader(String xItauAuth, String contaId) {
        if (StringUtils.isEmpty(xItauAuth) || StringUtils.isEmpty(contaId)) {
            throw new BusinessException(500, "IAAS_EXPIRATION_TOKEN", "AccessToken Inválido, gerar outro");
        }
    }
}
