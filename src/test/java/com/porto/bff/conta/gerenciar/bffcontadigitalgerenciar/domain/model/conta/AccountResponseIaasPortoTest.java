package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.conta;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountResponseIaasPortoTest {

    @Test
    void testWithFlags() {
        List<AccountFlagsResponseIaasPorto> originalFlags = List.of(
                new AccountFlagsResponseIaasPorto("1", "policy1", "timestamp1"),
                new AccountFlagsResponseIaasPorto("2", "policy2", "timestamp2")
        );

        AccountResponseIaasPorto originalInstance = new AccountResponseIaasPorto(
                "123", new BankAccountResponseIassPorto("teste", "teste", "teste", "teste"), "ACTIVE", "SAVINGS", originalFlags, "createdAt", "updatedAt"
        );

        List<AccountFlagsResponseIaasPorto> modifiedFlags = List.of(
                new AccountFlagsResponseIaasPorto("3", "policy3", "timestamp3"),
                new AccountFlagsResponseIaasPorto("4", "policy4", "timestamp4")
        );

        AccountResponseIaasPorto modifiedInstance = originalInstance.withFlags(modifiedFlags);

        assertEquals(modifiedFlags, modifiedInstance.flags());
        assertEquals(originalInstance.id(), modifiedInstance.id());
        assertEquals(originalInstance.bankAccount(), modifiedInstance.bankAccount());
        assertEquals(originalInstance.status(), modifiedInstance.status());
        assertEquals(originalInstance.type(), modifiedInstance.type());
        assertEquals(originalInstance.createdAt(), modifiedInstance.createdAt());
        assertEquals(originalInstance.updatedAt(), modifiedInstance.updatedAt());
    }
}