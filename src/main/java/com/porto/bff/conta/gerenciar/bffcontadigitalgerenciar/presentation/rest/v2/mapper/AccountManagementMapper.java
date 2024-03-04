package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v2.mapper;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.BackendResponseData;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.account.balance.v2.AccountBalanceEntityResponse;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v2.dto.AccountBalanceDtoResponse;
import com.porto.experiencia.cliente.conta.digital.commons.web.model.ApiResponseData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.text.NumberFormat;
import java.util.Locale;

import static org.apache.commons.lang3.StringUtils.SPACE;

@Mapper(componentModel = "spring")
public interface AccountManagementMapper {

    @Mapping(source = "data.available", target = "dados.available", qualifiedByName = "buildBalance")
    @Mapping(source = "data.reserved", target = "dados.reserved", qualifiedByName = "buildBalance")
    @Mapping(source = "data.blocked", target = "dados.blocked", qualifiedByName = "buildBalance")
    ApiResponseData<AccountBalanceDtoResponse> toDto(BackendResponseData<AccountBalanceEntityResponse> entity);

    @Named("buildBalance")
    default String buildBalance(Double balance) {
        var locale = new Locale("pt", "BR");
        var currencyInstance = NumberFormat.getCurrencyInstance(locale);
        return currencyInstance.format(balance).replaceAll("\\p{Z}", SPACE);
    }

}
