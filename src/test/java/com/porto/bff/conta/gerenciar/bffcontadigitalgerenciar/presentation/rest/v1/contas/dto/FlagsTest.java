package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v1.contas.dto;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.porto.AccountData;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.porto.Flags;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

class FlagsTest {

    @Test
    public void testDataAnnotation() {

        Flags flags = new Flags();


        flags.setDados(Arrays.asList(
                AccountData.builder().contaId("123").politicas(Arrays.asList("politica1", "politica2")).build(),
                AccountData.builder().contaId("456").politicas(Arrays.asList("politica3", "politica4")).build()
        ));


        assertThat(flags.getDados()).hasSize(2);
    }

    @Test
    public void testNoArgsConstructor() {

        Flags flags = new Flags();


        assertNull(flags.getDados());
    }

    @Test
    public void testAllArgsConstructor() {

        List<AccountData> dadosList = Arrays.asList(
                AccountData.builder().contaId("789").politicas(Arrays.asList("politica5", "politica6")).build(),
                AccountData.builder().contaId("012").politicas(Arrays.asList("politica7", "politica8")).build()
        );

        Flags flags = new Flags(dadosList);


        assertThat(flags.getDados()).isEqualTo(dadosList);
    }

    @Test
    public void testBuscaPoliticasPorContaId() {

        Flags flags = new Flags(Arrays.asList(
                AccountData.builder().contaId("123").politicas(Arrays.asList("politica1", "politica2")).build(),
                AccountData.builder().contaId("456").politicas(Arrays.asList("politica3", "politica4")).build()
        ));


        List<String> politicasConta123 = flags.buscaPoliticasPorContaId("123");
        List<String> politicasConta789 = flags.buscaPoliticasPorContaId("789");

        assertThat(politicasConta123).containsExactly("politica1", "politica2");
        assertThat(politicasConta789).isEmpty();
    }

    @Test
    public void testBuilder() {
        Flags flags = Flags.builder()
                .dados(Arrays.asList(
                        AccountData.builder().contaId("123").politicas(Arrays.asList("politica1", "politica2")).build(),
                        AccountData.builder().contaId("456").politicas(Arrays.asList("politica3", "politica4")).build()
                ))
                .build();

        assertThat(flags.getDados()).hasSize(2);
        assertThat(flags.getDados().get(0).getContaId()).isEqualTo("123");
        assertThat(flags.getDados().get(1).getPoliticas()).containsExactly("politica3", "politica4");
    }

    @Test
    public void testDataAnnotation2() {
        Flags flags = new Flags(Arrays.asList(
                AccountData.builder().contaId("123").politicas(Arrays.asList("politica1", "politica2")).build(),
                AccountData.builder().contaId("456").politicas(Arrays.asList("politica3", "politica4")).build()
        ));

        List<AccountData> retrievedDados = flags.getDados();
        assertThat(retrievedDados).hasSize(2);

        Flags sameFlags = new Flags(Arrays.asList(
                AccountData.builder().contaId("123").politicas(Arrays.asList("politica1", "politica2")).build(),
                AccountData.builder().contaId("456").politicas(Arrays.asList("politica3", "politica4")).build()
        ));
        assertThat(flags).isEqualTo(sameFlags);

        assertThat(flags.hashCode()).isEqualTo(sameFlags.hashCode());

        String toStringResult = flags.toString();
        assertThat(toStringResult).contains("dados=");

        flags.setDados(Arrays.asList(
                AccountData.builder().contaId("789").politicas(Arrays.asList("politica5", "politica6")).build()
        ));
        assertThat(flags.getDados()).hasSize(1);
    }




}