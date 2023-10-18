package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.conta;


import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.DataResponseIassPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.conta.AccountResponseIaasPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.saldo.BalanceResponseIaasPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.sumario.SumarioResponseIaasPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.conta.client.ContaIaasPortoClient;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.decodertoken.DecodificarAccessToken;
import com.porto.experiencia.cliente.conta.digital.commons.domain.exception.BusinessException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContaIassPortoAdapterImpl implements ContaIassPortoAdapter {

    private final ContaIaasPortoClient client;
    private final DecodificarAccessToken decodificador;

    @Override
    public DataResponseIassPorto<AccountResponseIaasPorto> getConta(String xItauAuth, String contaId) {
    	return this.client.findByIdContaIaas(this.getBearerInput(xItauAuth), "IAAS", contaId, contaId);
    }

    @Override
    public DataResponseIassPorto<BalanceResponseIaasPorto> getContaSaldo(String xItauAuth, String contaId) {
    	return this.client.findBySaldoContaIaas(getBearerInput(xItauAuth), "IAAS", contaId, contaId);
    }

    @Override
    public DataResponseIassPorto<SumarioResponseIaasPorto> sumarioConta(String tokenCognito, String xItauAuth, String contaId) {
            DataResponseIassPorto<AccountResponseIaasPorto> dadosConta =
                    this.client.findByIdContaIaas(this.getBearerInput(xItauAuth), "IAAS", contaId, contaId);
            DataResponseIassPorto<BalanceResponseIaasPorto> saldoConta =
                    this.client.findBySaldoContaIaas(getBearerInput(xItauAuth), "IAAS", contaId, contaId);
            return new DataResponseIassPorto<>(new SumarioResponseIaasPorto(this.decodificador.getCpfPorToken(tokenCognito), dadosConta, saldoConta));
    }

    private String getBearerInput(String xItauAuth) {
        String prefixBearer = "Bearer ";
        if (xItauAuth.contains(prefixBearer)) {
            return xItauAuth;
        }
        return prefixBearer + xItauAuth;
    }

}
