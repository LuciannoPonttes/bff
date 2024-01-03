package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.conta;


import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.DataResponseIassPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.cartoes.ListaCartoesResponse;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.conta.AccountFlagsResponseIaasPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.conta.AccountResponseIaasPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.conta.BankAccountResponseIassPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.saldo.BalanceResponseIaasPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.sumario.SumarioResponseIaasPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.conta.client.CartoesPortoClient;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.conta.client.ContaIaasPortoClient;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.decodertoken.DecodificarAccessToken;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.pix.client.PixManagementClient;
import com.porto.experiencia.cliente.conta.digital.commons.web.model.ApiResponseData;
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
//    	return this.client.findByIdContaIaas(this.getBearerInput(xItauAuth), "IAAS", contaId, contaId);


        List<AccountFlagsResponseIaasPorto> flags = List.of(
                new AccountFlagsResponseIaasPorto("1", "8a0ee28d-8e8c493d-ad68-d3583c0601f5", ""),
                new AccountFlagsResponseIaasPorto("2", "def1b676-295a4af3-9a3e5dbb18f18b53", ""),
                new AccountFlagsResponseIaasPorto("3", "98a22f32-7ac6-4228-998e83ad0b1e7c9d", "")
        );

//        List<AccountFlag> flags = List.of(
//                new AccountFlag("1", "51db4af4-d70e45d4-9958-fec755c35f1e", ""),
//                new AccountFlag("2", "0efb9706-9424-4b44-a7c0-802df2dd7806", "")
//        );

        AccountResponseIaasPorto accountResponseIaasPorto = new AccountResponseIaasPorto("1",new BankAccountResponseIassPorto("", "", "", ""),"true", "1",
                flags, "", "");

        return new DataResponseIassPorto<>(accountResponseIaasPorto);
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



        var quantidadeChavePix = obterQuantidadeChavesPixDaConta(tokenCognito, xItauAuth, contaId);
        return new DataResponseIassPorto<>(new SumarioResponseIaasPorto(
                this.decodificador.getCpfPorToken(tokenCognito),
                dadosConta,
                saldoConta,
                hasPortoCard,
                quantidadeChavePix));
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

    private String formatarMensagemParaExbirNoFront(int quantidade) {
        if(quantidade == 0) {
            return "Cadastre suas Chaves Pix";
        } else if (quantidade == 1) {
            return "1 Chave";
        } else {
            return quantidade+" Chaves";
        }
    }

    private String obterQuantidadeChavesPixDaConta(String tokenCognito, String xItauAuth, String contaId) {
        String mensagemChave = "";
        try {
            ApiResponseData<List<Object>> listChavePix = this.pixManagementClient
                    .getPixKeyFromAnAccount(contaId, tokenCognito, this.getBearerInput(xItauAuth));
            mensagemChave =  formatarMensagemParaExbirNoFront(listChavePix.dados().size());
            return mensagemChave;
        } catch (Exception e) {
            return mensagemChave;
        }
    }

}
