package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v2.mapper;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.BackendResponseData;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.saldo.v2.AccountBalanceEntityResponse;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v2.dto.AccountBalanceDtoResponse;
import com.porto.experiencia.cliente.conta.digital.commons.web.model.ApiResponseData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountManagementMapper {

    @Mapping(source = "data.available", target = "dados.disponivel")
    @Mapping(source = "data.reserved", target = "dados.reservada")
    @Mapping(source = "data.blocked", target = "dados.bloqueado")
    ApiResponseData<AccountBalanceDtoResponse> toDto(BackendResponseData<AccountBalanceEntityResponse> entity);

}
