package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v2.mapper;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.BackendResponseData;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.saldo.v2.AccountBalanceEntityResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountManagementMapperTest {
    AccountManagementMapper mapper = new AccountManagementMapperImpl();

    @Test
    void toDto() {
        var entity = new BackendResponseData<>(new AccountBalanceEntityResponse(10.49, 13.56, 12.51));
        var dto = this.mapper.toDto(entity);
        assertEquals(dto.dados().bloqueado(), entity.data().blocked());
        assertEquals(dto.dados().disponivel(), entity.data().available());
        assertEquals(dto.dados().reservada(), entity.data().reserved());
    }
}