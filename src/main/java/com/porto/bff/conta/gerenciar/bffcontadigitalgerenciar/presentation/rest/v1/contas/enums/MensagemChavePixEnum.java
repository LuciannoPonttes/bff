package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v1.contas.enums;

import lombok.Getter;

@Getter
public enum MensagemChavePixEnum {

    MENSAGEM_SEM_CHAVE_CADASTRADA("Cadastre suas Chaves Pix", 0),
    MENSAGEM_UMA_CHAVE_CADASTRADA("Chave", 1),
    MENSAGEM_MAIS_DE_UMA_CHAVE_CADASTRADA("Chaves", 2);

    private final String msg;
    private final int qnt;


    MensagemChavePixEnum(String msg, int qnt) {
        this.msg = msg;
        this.qnt = qnt;
    }

    public static MensagemChavePixEnum getMensagemPorQuantidade(int quantidade) {
        for (MensagemChavePixEnum mensagem : values()) {
            if (mensagem.qnt == quantidade) {
                return mensagem;
            }
        }
        throw new IllegalArgumentException("Quantidade inválida: " + quantidade);
    }
}
