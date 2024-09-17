package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.porto;



import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.porto.client.AccountIaasPortoClient;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.porto.decodertoken.DecoderAccessToken;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.summary.v2.out.response.PortoCardResponse;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.porto.AccountResponseIaasPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.porto.BalanceResponseIaasPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.porto.DataResponseIassPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.porto.SummaryResponseIaasPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.porto.client.PortoCardClient;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.porto.client.PixManagementClient;
import com.porto.experiencia.cliente.conta.digital.commons.web.model.ApiResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AccountIassPortoAdapterImpl implements AccountIassPortoAdapter {

    private final AccountIaasPortoClient client;
    private final PortoCardClient cartoesPortoClient;
    private final DecoderAccessToken decodificador;

    private final PixManagementClient pixManagementClient;


    @Override
    public DataResponseIassPorto<AccountResponseIaasPorto> getAccount(String xItauAuth, String contaId) {
        return this.client.findByIdContaIaas(this.getBearerInput(xItauAuth), "IAAS", contaId, contaId);
    }

    @Override
    public DataResponseIassPorto<BalanceResponseIaasPorto> getBalance(String xItauAuth, String contaId) {
        return this.client.findBySaldoContaIaas(getBearerInput(xItauAuth), "IAAS", contaId, contaId);
    }

    @Override
    public DataResponseIassPorto<SummaryResponseIaasPorto> getSummary(String tokenCognito, String xItauAuth, String contaId) {
        DataResponseIassPorto<AccountResponseIaasPorto> dadosConta =
                this.client.findByIdContaIaas(this.getBearerInput(xItauAuth), "IAAS", contaId, contaId);
        DataResponseIassPorto<BalanceResponseIaasPorto> saldoConta =
                this.client.findBySaldoContaIaas(getBearerInput(xItauAuth), "IAAS", contaId, contaId);
        boolean hasPortoCard = verificaExistenciaCartao(tokenCognito);


        var quantidadeChavePix = obterQuantidadeChavesPixDaConta(tokenCognito, xItauAuth, contaId);
        return new DataResponseIassPorto<>(new SummaryResponseIaasPorto(
                this.decodificador.getCpfPorToken(tokenCognito),
                dadosConta,
                saldoConta,
                hasPortoCard,
                quantidadeChavePix));
    }


    private boolean verificaExistenciaCartao(String tokenCognito) {
        PortoCardResponse cardsByUser = null;

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

     public static String buildMessagePixKeys(int quantidade) {
        if (quantidade == 0) {
            return "Cadastre suas Chaves Pix";
        } else if (quantidade == 1) {
            return "1 Chave";
        } else {
            return quantidade + " Chaves";
        }
    }

    String obterQuantidadeChavesPixDaConta(String tokenCognito, String xItauAuth, String contaId) {
        String mensagemChave = "";
        try {
            ApiResponseData<List<Object>> listChavePix = this.pixManagementClient
                    .getPixKeyFromAnAccount(contaId, tokenCognito, this.getBearerInput(xItauAuth));
            mensagemChave = buildMessagePixKeys(listChavePix.dados().size());
            return mensagemChave;
        } catch (Exception e) {
            return mensagemChave;
        }
    }

}
