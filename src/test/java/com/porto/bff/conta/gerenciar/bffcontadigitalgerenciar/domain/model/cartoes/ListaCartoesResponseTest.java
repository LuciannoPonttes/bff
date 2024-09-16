package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.cartoes;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.summary.v2.out.response.CustomerResponse;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.summary.v2.out.response.ListCardsResponseBody;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.summary.v2.out.response.PortoCardResponseData;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.summary.v2.out.response.PortoCardResponse;
import org.junit.jupiter.api.Test;

import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;

class ListaCartoesResponseTest {

    @Test
    public void testListaCartoesResponse() {
        // Given
        PortoCardResponseData dados = PortoCardResponseData.builder()
                .codigoProduto("PROD001")
                .cliente(CustomerResponse.builder().email("test@example.com").nome("John Doe").build())
                .lista(List.of(
                        ListCardsResponseBody.builder().estado("Ativo").build(),
                        ListCardsResponseBody.builder().estado("Inativo").build()
                ))
                .build();

        // When
        PortoCardResponse listaCartoesResponse = new PortoCardResponse();
        listaCartoesResponse.setDados(dados);

        // Then
        assertThat(listaCartoesResponse.getDados()).isEqualTo(dados);
    }
}