package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v1.contas.dto;

import java.util.Arrays;
import java.util.List;

public enum PoliticasBloqueioContaEnum {
    OBITO("8a0ee28d-8e8c493d-ad68-d3583c0601f5", true, false),
    BLOQUEIO_JUDICIAL("def1b676-295a4af3-9a3e5dbb18f18b53", false, false),
    BLOQUEIO_CASHIN_CASHOUT("98a22f32-7ac6-4228-998e83ad0b1e7c9d", false, false),
    CONTA_FRIA("6b935a29-ec58-4116-b3a4-64b44aadb3e5", false, false),
    ANALISE_DOCUMENTO("1d61e771-7d6b4503-ab22-2dbcff00baf7", false, false),
    MED_269("dfd044de-0ab6-4cdb-bf01-16ac11eccd3a", true, false),
    RASPA_CONTA_JUDICIAL("0eec7720-c493-44fe-8a70-a24f93e5cb0b", true , false),
    FRAUDES_CONTROL_TOWER("7526094e-fe86-46d4-81d3-fd60a46a3d9b", true, false),
    SUSPEITA_FRAUDE("c76e0255-17af45be-a5b6-6c57d58abf49", true, false),
    PERDA_ROUBO("c179c29f-d2c1-4c81-bf33-18afd52dbe2f", true, false),
    QUESTIONAMENTO_APROFUNDADO_PLD("fbf43558-2180-4a70-b4eb5a222b5c60f2", false, false),
    BLOQUEIO_CAUTELAR("67fdd25e-bb46-4324-bc5ef5c03ac26699", false, false),
    POTENCIAL_DESINTERESSE("0da68bd4-8b54-4f0b-bfd1-e6741bba6890", true, false),
    CONTESTACAO_CONTA_RECONHECIDA("51db4af4-d70e45d4-9958-fec755c35f1e", false, true),
    PRE_ENCERRAMENTO_CONTA("0efb9706-9424-4b44-a7c0-802df2dd7806", false, true);

    public final String politica;
    public final boolean cashIn;
    public final boolean cashOut;

    PoliticasBloqueioContaEnum(String politica, boolean cashIn, boolean cashOut) {
        this.politica = politica;
        this.cashIn = cashIn;
        this.cashOut = cashOut;
    }

    public static boolean permiteCashIn(List<String> politicasBloqueio) {

        List<PoliticasBloqueioContaEnum> politicas = Arrays.stream(PoliticasBloqueioContaEnum.values()).filter(pu -> politicasBloqueio.contains(pu.politica)).toList();

        if (politicas.isEmpty()) return true;

        return politicas.stream().allMatch(policy -> policy.cashIn);
    }

    public static boolean permiteCashOut(List<String> politicasBloqueio) {

        List<PoliticasBloqueioContaEnum> politicas = Arrays.stream(PoliticasBloqueioContaEnum.values()).filter(pu -> politicasBloqueio.contains(pu.politica)).toList();

        if (politicas.isEmpty()) return true;

        return politicas.stream().allMatch(policy -> policy.cashOut);
    }
}
