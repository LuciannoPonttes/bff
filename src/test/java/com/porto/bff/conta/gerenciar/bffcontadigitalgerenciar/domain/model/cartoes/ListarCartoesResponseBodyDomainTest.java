package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.cartoes;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.summary.v2.out.response.ListCardsResponseBody;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class ListarCartoesResponseBodyDomainTest {

    @Test
    public void testListarCartoesResponseBodyDomainBuilder() {
        // Given
        String estado = "Ativo";
        String bandeira = "MasterCard";
        String descricaoFormatada = "**** **** **** 1234";
        String finalCartao = "1234";
        String flagTitular = "true";
        String melhorDataCompra = "25";
        String cartaoTipo = "Crédito";
        String cartaoLogoCode = "MC";
        String corPlastico = "Azul";
        String statusBloqueio = "Desbloqueado";

        // When
        ListCardsResponseBody cartao = ListCardsResponseBody.builder()
                .estado(estado)
                .bandeira(bandeira)
                .descricaoFormatada(descricaoFormatada)
                .finalCartao(finalCartao)
                .flagTitular(flagTitular)
                .melhorDataCompra(melhorDataCompra)
                .cartaoTipo(cartaoTipo)
                .cartaoLogoCode(cartaoLogoCode)
                .corPlastico(corPlastico)
                .statusBloqueio(statusBloqueio)
                .build();

        // Then
        assertThat(cartao.getEstado()).isEqualTo(estado);
        assertThat(cartao.getBandeira()).isEqualTo(bandeira);
        assertThat(cartao.getDescricaoFormatada()).isEqualTo(descricaoFormatada);
        assertThat(cartao.getFinalCartao()).isEqualTo(finalCartao);
        assertThat(cartao.getFlagTitular()).isEqualTo(flagTitular);
        assertThat(cartao.getMelhorDataCompra()).isEqualTo(melhorDataCompra);
        assertThat(cartao.getCartaoTipo()).isEqualTo(cartaoTipo);
        assertThat(cartao.getCartaoLogoCode()).isEqualTo(cartaoLogoCode);
        assertThat(cartao.getCorPlastico()).isEqualTo(corPlastico);
        assertThat(cartao.getStatusBloqueio()).isEqualTo(statusBloqueio);
    }

    @Test
    public void testListarCartoesResponseBodyDomainNoArgsConstructor() {
        ListCardsResponseBody cartao = new ListCardsResponseBody();

        assertThat(cartao.getEstado()).isNull();
        assertThat(cartao.getBandeira()).isNull();
        assertThat(cartao.getDescricaoFormatada()).isNull();
        assertThat(cartao.getFinalCartao()).isNull();
        assertThat(cartao.getFlagTitular()).isNull();
        assertThat(cartao.getMelhorDataCompra()).isNull();
        assertThat(cartao.getCartaoTipo()).isNull();
        assertThat(cartao.getCartaoLogoCode()).isNull();
        assertThat(cartao.getCorPlastico()).isNull();
        assertThat(cartao.getStatusBloqueio()).isNull();
    }

    @Test
    public void testListarCartoesResponseBodyDomainAllArgsConstructor() {
        String estado = "Ativo";
        String bandeira = "MasterCard";
        String descricaoFormatada = "**** **** **** 1234";
        String finalCartao = "1234";
        String flagTitular = "true";
        String melhorDataCompra = "25";
        String cartaoTipo = "Crédito";
        String cartaoLogoCode = "MC";
        String corPlastico = "Azul";
        String statusBloqueio = "Desbloqueado";

        ListCardsResponseBody cartao = new ListCardsResponseBody(
                estado, bandeira, descricaoFormatada, finalCartao, flagTitular, melhorDataCompra,
                cartaoTipo, cartaoLogoCode, corPlastico, statusBloqueio
        );

        cartao.setEstado(estado);

        assertThat(cartao.getEstado()).isEqualTo(estado);
        assertThat(cartao.getBandeira()).isEqualTo(bandeira);
        assertThat(cartao.getDescricaoFormatada()).isEqualTo(descricaoFormatada);
        assertThat(cartao.getFinalCartao()).isEqualTo(finalCartao);
        assertThat(cartao.getFlagTitular()).isEqualTo(flagTitular);
        assertThat(cartao.getMelhorDataCompra()).isEqualTo(melhorDataCompra);
        assertThat(cartao.getCartaoTipo()).isEqualTo(cartaoTipo);
        assertThat(cartao.getCartaoLogoCode()).isEqualTo(cartaoLogoCode);
        assertThat(cartao.getCorPlastico()).isEqualTo(corPlastico);
        assertThat(cartao.getStatusBloqueio()).isEqualTo(statusBloqueio);
    }

    private ListCardsResponseBody getListarCartoesResponseBodyDomain() {
        String estado = "Ativo";
        String bandeira = "MasterCard";
        String descricaoFormatada = "**** **** **** 1234";
        String finalCartao = "1234";
        String flagTitular = "true";
        String melhorDataCompra = "25";
        String cartaoTipo = "Crédito";
        String cartaoLogoCode = "MC";
        String corPlastico = "Azul";
        String statusBloqueio = "Desbloqueado";

        ListCardsResponseBody cartao = ListCardsResponseBody.builder()
                .estado(estado)
                .bandeira(bandeira)
                .descricaoFormatada(descricaoFormatada)
                .finalCartao(finalCartao)
                .flagTitular(flagTitular)
                .melhorDataCompra(melhorDataCompra)
                .cartaoTipo(cartaoTipo)
                .cartaoLogoCode(cartaoLogoCode)
                .corPlastico(corPlastico)
                .statusBloqueio(statusBloqueio)
                .build();

        return cartao;
    }
}