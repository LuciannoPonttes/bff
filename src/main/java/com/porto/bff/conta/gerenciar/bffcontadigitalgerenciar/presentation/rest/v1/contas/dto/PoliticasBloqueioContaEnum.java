package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v1.contas.dto;

import java.util.Arrays;
import java.util.List;

public enum PoliticasBloqueioContaEnum {
    OBITO("8a0ee28d-8e8c493d-ad68-d3583c0601f5", true, false),
    BLOQUEIO_JUDICIAL("def1b676-295a4af3-9a3e5dbb18f18b53", true, false),
    Bloqueio_Cashin_Cashout("98a22f32-7ac6-4228-998e83ad0b1e7c9d", true, false),
    Conta_Fria("6b935a29-ec58-4116-b3a4-64b44aadb3e5", false, false),
    Contestacao_Conta_Reconhecida("51db4af4-d70e45d4-9958-fec755c35f1e", false, true),
    Pre_Encerramento_Conta("0efb9706-9424-4b44-a7c0-802df2dd7806", false, true);

    public final String descricao;
    public final boolean cashIn;
    public final boolean cashOut;

    PoliticasBloqueioContaEnum(String descricao, boolean cashIn, boolean cashOut) {
        this.descricao = descricao;
        this.cashIn = cashIn;
        this.cashOut = cashOut;
    }

    public static boolean permiteCashIn(List<String> policies) {

        if (policies == null) return true;

        List<PoliticasBloqueioContaEnum> poli = Arrays.stream(PoliticasBloqueioContaEnum.values()).filter(pu -> policies.contains(pu.descricao)).toList();

        if (poli.isEmpty()) return true;

        return poli.stream().allMatch(policy -> policy.cashIn);
    }

    public static boolean permiteCashOut(List<String> policies) {

        if (policies == null) return true;

        List<PoliticasBloqueioContaEnum> poli = Arrays.stream(PoliticasBloqueioContaEnum.values()).filter(pu -> policies.contains(pu.descricao)).toList();

        if (poli.isEmpty()) return true;

        return poli.stream().allMatch(policy -> policy.cashOut);
    }
}
