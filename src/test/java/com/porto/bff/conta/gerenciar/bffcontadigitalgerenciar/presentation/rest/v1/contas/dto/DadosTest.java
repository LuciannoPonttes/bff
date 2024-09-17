package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v1.contas.dto;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.porto.AccountData;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

class DadosTest {

    @Test
    public void testBuilder() {
        String contaId = "123";
        List<String> politicas = Arrays.asList("politica1", "politica2");

        AccountData dados = AccountData.builder()
                .contaId(contaId)
                .politicas(politicas)
                .build();

        assertThat(dados.getContaId()).isEqualTo(contaId);
        assertThat(dados.getPoliticas()).isEqualTo(politicas);
    }

    @Test
    public void testAllArgsConstructor() {
        String contaId = "456";
        List<String> politicas = Arrays.asList("politica3", "politica4");

        AccountData dados = new AccountData(contaId, politicas);

        assertThat(dados.getContaId()).isEqualTo(contaId);
        assertThat(dados.getPoliticas()).isEqualTo(politicas);
    }

    @Test
    public void testDataAnnotation() {
        AccountData dados = new AccountData();

        dados.setContaId("123");
        dados.setPoliticas(Arrays.asList("politica1", "politica2"));

        assertThat(dados.getContaId()).isEqualTo("123");
        assertThat(dados.getPoliticas()).containsExactly("politica1", "politica2");
    }

    @Test
    public void testNoArgsConstructor() {
        AccountData dados = new AccountData();

        assertNull(dados.getContaId());
        assertNull(dados.getPoliticas());
    }

    @Test
    public void testSetterAnnotation() {
        AccountData dados = new AccountData("456", Arrays.asList("politica3", "politica4"));

        dados.setContaId("789");
        dados.setPoliticas(Arrays.asList("politica5", "politica6"));

        assertThat(dados.getContaId()).isEqualTo("789");
        assertThat(dados.getPoliticas()).containsExactly("politica5", "politica6");
    }
}