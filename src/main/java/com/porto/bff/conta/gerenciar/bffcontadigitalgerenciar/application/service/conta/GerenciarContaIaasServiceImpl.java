package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.service.conta;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.DataResponseIassPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.conta.AccountResponseIaasPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.saldo.BalanceResponseIaasPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.sumario.SumarioResponseIaasPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.conta.ContaIassPortoAdapter;
import com.porto.experiencia.cliente.conta.digital.commons.domain.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;



@Service
@RequiredArgsConstructor
public class GerenciarContaIaasServiceImpl implements GerenciarContaIaasService {

    private final ContaIassPortoAdapter adapter;


    @Override
    public DataResponseIassPorto<AccountResponseIaasPorto> getConta(String xItauAuth, String contaId) {
        isValidHeaderProjet(xItauAuth, contaId);
        return this.adapter.getConta(xItauAuth, contaId);
    }

    @Override
    public DataResponseIassPorto<BalanceResponseIaasPorto> getContaSaldo(String xItauAuth, String contaId) {
        isValidHeaderProjet(xItauAuth, contaId);
        return this.adapter.getContaSaldo(xItauAuth, contaId);
    }

    @Override
    public DataResponseIassPorto<SumarioResponseIaasPorto> contaSumario(String tokenCognito, String xItauAuth, String contaId) {
        isValidHeaderProjet(xItauAuth, contaId);
        return adapter.sumarioConta(tokenCognito, xItauAuth, contaId);
    }

    private void isValidHeaderProjet(String xItauAuth, String contaId) {
        if (StringUtils.isEmpty(xItauAuth) || StringUtils.isEmpty(contaId)) {
            throw new BusinessException(500, "IAAS_EXPIRATION_TOKEN", "AccessToken Inválido, gerar outro");
        }
    }

}
