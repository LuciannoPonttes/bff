package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.conta;


import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.DataResponseIassPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.cartoes.ListaCartoesResponse;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.conta.AccountResponseIaasPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.saldo.BalanceResponseIaasPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.sumario.SumarioResponseIaasPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.conta.client.CartoesPortoClient;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.conta.client.ContaIaasPortoClient;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.decodertoken.DecodificarAccessToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContaIassPortoAdapterImpl implements ContaIassPortoAdapter {

    private final ContaIaasPortoClient client;
    private final CartoesPortoClient cartoesPortoClient;
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
            boolean hasPortoCard = verificaExistenciaCartao(tokenCognito);

            return new DataResponseIassPorto<>(new SumarioResponseIaasPorto(
                    this.decodificador.getCpfPorToken(tokenCognito),
                    dadosConta,
                    saldoConta,
                    hasPortoCard));
    }

    private boolean verificaExistenciaCartao(String tokenCognito) {
        ListaCartoesResponse cardsByUser = null;

        try {
            cardsByUser = this.cartoesPortoClient.getCardsByuser(tokenCognito);
        } catch (Exception ignored) {}

        return cardsByUser != null && !cardsByUser.getDados().getLista().isEmpty();
    }

    private String getBearerInput(String xItauAuth) {
        String prefixBearer = "Bearer ";
        if (xItauAuth.contains(prefixBearer)) {
            return xItauAuth;
        }
        return prefixBearer + xItauAuth;
    }

}
