package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.cartoes;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.summary.v2.out.response.CustomerResponse;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ClienteDomainTest {

    @Test
    public void testClienteDomainBuilder() {
        String email = "test@example.com";
        String nome = "John Doe";

        CustomerResponse cliente = CustomerResponse.builder()
                .email(email)
                .nome(nome)
                .build();

        assertThat(cliente.getEmail()).isEqualTo(email);
        assertThat(cliente.getNome()).isEqualTo(nome);
    }

    @Test
    public void testClienteDomainNoArgsConstructor() {
        CustomerResponse cliente = new CustomerResponse();

        assertThat(cliente.getEmail()).isNull();
        assertThat(cliente.getNome()).isNull();
    }

    @Test
    public void testClienteDomainAllArgsConstructor() {
        String email = "test@example.com";
        String nome = "John Doe";

        CustomerResponse cliente = new CustomerResponse(email, nome);
        cliente.setEmail(email);
        cliente.setNome(nome);
        assertThat(cliente.getEmail()).isEqualTo(email);
        assertThat(cliente.getNome()).isEqualTo(nome);
    }
}