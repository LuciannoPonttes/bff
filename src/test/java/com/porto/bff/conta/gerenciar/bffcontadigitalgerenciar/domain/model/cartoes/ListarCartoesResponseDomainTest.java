package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.cartoes;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.summary.v2.out.response.CustomerResponse;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.summary.v2.out.response.ListCardsResponseBody;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.summary.v2.out.response.PortoCardResponseData;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ListarCartoesResponseDomainTest {

    @Test
    public void testListarCartoesResponseDomainBuilder() {
        String codigoProduto = "PROD001";
        CustomerResponse cliente = CustomerResponse.builder().email("test@example.com").nome("John Doe").build();
        List<ListCardsResponseBody> lista = List.of(
                ListCardsResponseBody.builder().estado("Ativo").build(),
                ListCardsResponseBody.builder().estado("Inativo").build()
        );

        PortoCardResponseData response = PortoCardResponseData.builder()
                .codigoProduto(codigoProduto)
                .cliente(cliente)
                .lista(lista)
                .build();

        assertThat(response.getCodigoProduto()).isEqualTo(codigoProduto);
        assertThat(response.getCliente()).isEqualTo(cliente);
        assertThat(response.getLista()).isEqualTo(lista);
    }

    @Test
    public void testListarCartoesResponseDomainNoArgsConstructor() {
        PortoCardResponseData response = new PortoCardResponseData();

        assertThat(response.getCodigoProduto()).isNull();
        assertThat(response.getCliente()).isNull();
        assertThat(response.getLista()).isNull();
    }

    @Test
    public void testListarCartoesResponseDomainAllArgsConstructor() {
        String codigoProduto = "PROD001";
        CustomerResponse cliente = CustomerResponse.builder().email("test@example.com").nome("John Doe").build();
        List<ListCardsResponseBody> lista = List.of(
                ListCardsResponseBody.builder().estado("Ativo").build(),
                ListCardsResponseBody.builder().estado("Inativo").build()
        );

        PortoCardResponseData response = new PortoCardResponseData(codigoProduto, cliente, lista);
        response.setCodigoProduto(codigoProduto);
        assertThat(response.getCodigoProduto()).isEqualTo(codigoProduto);
        assertThat(response.getCliente()).isEqualTo(cliente);
        assertThat(response.getLista()).isEqualTo(lista);
    }
}