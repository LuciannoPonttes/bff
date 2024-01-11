package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.sumario;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.DataResponseIassPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.conta.AccountResponseIaasPorto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SumarioResponseIaasPortoTest {


    @Test
    void testWithAccount() {
        AccountResponseIaasPorto originalAccount = new AccountResponseIaasPorto("accountId", null, "Active", "Savings", null, "2022-01-11", "2022-01-11");
        DataResponseIassPorto<AccountResponseIaasPorto> originalDataResponse = new DataResponseIassPorto<>(originalAccount);
        SumarioResponseIaasPorto originalSumario = new SumarioResponseIaasPorto("document", originalDataResponse, null, true, "3");

        AccountResponseIaasPorto modifiedAccount = new AccountResponseIaasPorto("accountId", null, "Blocked", "Checking", null, "2022-01-11", "2022-01-11");
        DataResponseIassPorto<AccountResponseIaasPorto> modifiedDataResponse = new DataResponseIassPorto<>(modifiedAccount);

        SumarioResponseIaasPorto modifiedSumario = originalSumario.withAccount(modifiedDataResponse);

        assertEquals(modifiedAccount, modifiedSumario.account().data());
        assertEquals(originalSumario.balance(), modifiedSumario.balance()); // Ensure other fields remain unchanged
        assertEquals(originalSumario.hasPortoCard(), modifiedSumario.hasPortoCard());
        assertEquals(originalSumario.quantidadeChavePix(), modifiedSumario.quantidadeChavePix());
    }
}