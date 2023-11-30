package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.conta;


import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.DataResponseBFF;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.DataResponseIassPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.cartoes.ListaCartoesResponse;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.conta.AccountResponseIaasPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.pix.KeyPixSearchWithClaimDto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.pix.ListChavePixResponse;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.saldo.BalanceResponseIaasPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.sumario.SumarioResponseIaasPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.conta.client.CartoesPortoClient;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.conta.client.ContaIaasPortoClient;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.decodertoken.DecodificarAccessToken;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.pix.client.PixManagementClient;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v1.contas.enums.MensagemChavePixEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ContaIassPortoAdapterImpl implements ContaIassPortoAdapter {

    private final ContaIaasPortoClient client;
    private final CartoesPortoClient cartoesPortoClient;
    private final DecodificarAccessToken decodificador;

    private final PixManagementClient pixManagementClient;


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
        DataResponseBFF<List<KeyPixSearchWithClaimDto>> listChavePix = this.pixManagementClient
                .getPixKeyFromAnAccount(
                        contaId,
                        tokenCognito,
                        this.getBearerInput(xItauAuth)
                );

        var msgFormatada = getMensagemFormatado(listChavePix.dados().size());
        return new DataResponseIassPorto<>(new SumarioResponseIaasPorto(
                this.decodificador.getCpfPorToken(tokenCognito),
                dadosConta,
                saldoConta,
                hasPortoCard,
                msgFormatada));
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

    private String getMensagemFormatado(int quantidade) {
        if(quantidade == 0) {
            return "Cadastre suas Chaves Pix";
        } else if (quantidade == 1) {
            return "1 Chave";
        } else {
            return quantidade+" "+"Chaves";
        }
    }

}
