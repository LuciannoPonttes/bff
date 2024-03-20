package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v2.mapper;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.BackendResponseData;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.account.balance.v2.AccountBalanceEntityResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountManagementMapperTest {
    AccountManagementMapper mapper = new AccountManagementMapperImpl();

    @Test
    void toDto() {
        var entity = new BackendResponseData<>(new AccountBalanceEntityResponse(10.49, 13.56, 12.51));
        var dto = this.mapper.toAccountBalanceDto(entity);
        assertEquals("R$ 12,51", dto.dados().blocked());
        assertEquals("R$ 10,49",dto.dados().available());
        assertEquals("R$ 13,56",dto.dados().reserved());
    }
}