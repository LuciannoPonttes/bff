package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v1.contas.dto;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.porto.PoliticasBloqueioContaEnum;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
class PoliticasBloqueioContaEnumTest {
    @Test
    public void testPermiteCashIn_AllowWhenPolicyDoesNotExists() {
        List<String> flagsPolicies = List.of("ALLOW_CASH_IN_DESCRIPTION_POLICY_DOES_NOT_EXISTS");
        assertTrue(PoliticasBloqueioContaEnum.permiteCashIn(flagsPolicies));
    }

    @Test
    public void testPermiteCashOut_AllowWhenPolicyDoesNotExists() {
        List<String> flagsPolicies = List.of("ALLOW_CASH_OUT_DESCRIPTION_POLICY_DOES_NOT_EXISTS");
        assertTrue(PoliticasBloqueioContaEnum.permiteCashOut(flagsPolicies));
    }

    @Test
    public void testPermiteCashIn_Empty() {
        List<String> flagsPolicies = List.of();
        assertTrue(PoliticasBloqueioContaEnum.permiteCashIn(flagsPolicies));
    }


    @Test
    public void testPermiteCashOut_Empty() {
        List<String> flagsPolicies = List.of();
        assertTrue(PoliticasBloqueioContaEnum.permiteCashOut(flagsPolicies));
    }

    @Test
    public void deveSerFalsoQuandoObitoEBloqueioJudicialTemAoMenosUmValorFalso() {
        List<String> flagsPolicies = List.of("8a0ee28d-8e8c493d-ad68-d3583c0601f5", "def1b676-295a4af3-9a3e5dbb18f18b53"); //"OBITO", "BLOQUEIO_JUDICIAL"
        assertFalse(PoliticasBloqueioContaEnum.permiteCashIn(flagsPolicies));
        assertFalse(PoliticasBloqueioContaEnum.permiteCashOut(flagsPolicies));
    }

    @Test
    public void deveSerFalsoQuandoBloqueioCashinCashoutEContaFriaTemAoMenosUmValorFalso() {
        List<String> flagsPolicies = List.of("98a22f32-7ac6-4228-998e83ad0b1e7c9d", "6b935a29-ec58-4116-b3a4-64b44aadb3e5"); //"BLOQUEIO_CASHIN_CASHOUT", "CONTA_FRIA"
        assertFalse(PoliticasBloqueioContaEnum.permiteCashIn(flagsPolicies));
        assertFalse(PoliticasBloqueioContaEnum.permiteCashOut(flagsPolicies));
    }

    @Test
    public void deveSerFalsoQuandoBloqueioCautelarEContestacaoContaReconhecidaTemAoMenosUmValorFalso() {
        List<String> flagsPolicies = List.of("67fdd25e-bb46-4324-bc5ef5c03ac26699", "51db4af4-d70e45d4-9958-fec755c35f1e"); //"BLOQUEIO_CAUTELAR", "CONTESTACAO_CONTA_RECONHECIDA"
        assertFalse(PoliticasBloqueioContaEnum.permiteCashIn(flagsPolicies));
        assertFalse(PoliticasBloqueioContaEnum.permiteCashOut(flagsPolicies));
    }

    @Test
    public void deveSerFalsoQuandoUmaPoliticaTemValorFalsoEComparaComUmaPoliticaQueNaoExiste() {
        List<String> flagsPolicies = List.of("67fdd25e-bb46-4324-bc5ef5c03ac26699", "NAO_EXISTE"); //"BLOQUEIO_CAUTELAR", "NAO_EXISTE"
        assertFalse(PoliticasBloqueioContaEnum.permiteCashIn(flagsPolicies));
        assertFalse(PoliticasBloqueioContaEnum.permiteCashOut(flagsPolicies));
    }

    @Test
    public void deveSerVerdadeiroQuandoUmaPoliticaTemValorVerdadeiroEComparaComUmaPoliticaQueNaoExiste() {
        List<String> flagsPolicies = List.of("0eec7720-c493-44fe-8a70-a24f93e5cb0b", "NAO_EXISTE"); //"RASPA_CONTA_JUDICIAL", "NAO_EXISTE"
        assertTrue(PoliticasBloqueioContaEnum.permiteCashIn(flagsPolicies));
        assertFalse(PoliticasBloqueioContaEnum.permiteCashOut(flagsPolicies));
    }

}