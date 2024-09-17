package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.sumario;

//import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.DataResponseIassPorto;

/*
class SumarioResponseIaasPortoTest {


    @Test
    void testWithAccount() {
        AccountResponseIaasPorto originalAccount = new AccountResponseIaasPorto("accountId", null, "Active", "Savings", null, "2022-01-11", "2022-01-11");
        DataResponseIassPorto<AccountResponseIaasPorto> originalDataResponse = new DataResponseIassPorto<>(originalAccount);
        SummaryResponseIaasPorto originalSumario = new SummaryResponseIaasPorto("document", originalDataResponse, null, true, "3");

        AccountResponseIaasPorto modifiedAccount = new AccountResponseIaasPorto("accountId", null, "Blocked", "Checking", null, "2022-01-11", "2022-01-11");
        DataResponseIassPorto<AccountResponseIaasPorto> modifiedDataResponse = new DataResponseIassPorto<>(modifiedAccount);

        SummaryResponseIaasPorto modifiedSumario = originalSumario.withAccount(modifiedDataResponse);

        assertEquals(modifiedAccount, modifiedSumario.account().data());
        assertEquals(originalSumario.balance(), modifiedSumario.balance()); // Ensure other fields remain unchanged
        assertEquals(originalSumario.hasPortoCard(), modifiedSumario.hasPortoCard());
        assertEquals(originalSumario.quantidadeChavePix(), modifiedSumario.quantidadeChavePix());
    }
}*/