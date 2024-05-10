package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.cartoes;

import org.junit.jupiter.api.Test;

import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;

class ListaCartoesResponseTest {

    @Test
    public void testListaCartoesResponse() {
        // Given
        PortoCardResponseData dados = PortoCardResponseData.builder()
                .codigoProduto("PROD001")
                .cliente(ClienteDomain.builder().email("test@example.com").nome("John Doe").build())
                .lista(List.of(
                        ListarCartoesResponseBodyDomain.builder().estado("Ativo").build(),
                        ListarCartoesResponseBodyDomain.builder().estado("Inativo").build()
                ))
                .build();

        // When
        PortoCardResponse listaCartoesResponse = new PortoCardResponse();
        listaCartoesResponse.setDados(dados);

        // Then
        assertThat(listaCartoesResponse.getDados()).isEqualTo(dados);
    }
}