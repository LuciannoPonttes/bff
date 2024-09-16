package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.cartoes;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.summary.v2.out.response.PortoCardResponseData;


public class PortoCardResponse {
    private PortoCardResponseData dados;

    public PortoCardResponseData getDados() {
        return dados;
    }

    public void setDados(PortoCardResponseData dados) {
        this.dados = dados;
    }
}
