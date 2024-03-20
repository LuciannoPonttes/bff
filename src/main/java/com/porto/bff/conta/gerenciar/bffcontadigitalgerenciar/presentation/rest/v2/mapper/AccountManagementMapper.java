package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v2.mapper;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.BackendResponseData;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.account.balance.v2.AccountBalanceEntityResponse;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.account.data.v2.AccountDataEntityResponse;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v2.dto.AccountBalanceDtoResponse;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v2.dto.AccountDataDtoResponse;
import com.porto.experiencia.cliente.conta.digital.commons.web.model.ApiResponseData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.text.NumberFormat;
import java.util.Locale;

import static org.apache.commons.lang3.StringUtils.SPACE;

@Mapper(componentModel = "spring")
public interface AccountManagementMapper {

    String BANK_NAME = "Porto Bank - Banco Itaucard S/A";

    @Mapping(source = "data.available", target = "dados.available", qualifiedByName = "buildBalance")
    @Mapping(source = "data.reserved", target = "dados.reserved", qualifiedByName = "buildBalance")
    @Mapping(source = "data.blocked", target = "dados.blocked", qualifiedByName = "buildBalance")
    ApiResponseData<AccountBalanceDtoResponse> toAccountBalanceDto(BackendResponseData<AccountBalanceEntityResponse> entity);

    @Named("buildBalance")
    default String buildBalance(Double balance) {
        var locale = new Locale("pt", "BR");
        var currencyInstance = NumberFormat.getCurrencyInstance(locale);
        return currencyInstance.format(balance).replaceAll("\\p{Z}", SPACE);
    }

    @Mapping(source = "data.id", target = "dados.id")
    @Mapping(source = "data.status", target = "dados.status")
    @Mapping(source = "data.type", target = "dados.type")
    @Mapping(source = "data.createdAt", target = "dados.createdAt")
    @Mapping(source = "data.updatedAt", target = "dados.updatedAt")
    @Mapping(source = "data.bankAccount.bank", target = "dados.bankAccount.bank")
    @Mapping(source = "data.bankAccount.branch", target = "dados.bankAccount.branch")
    @Mapping(source = "data.bankAccount.number", target = "dados.bankAccount.number")
    @Mapping(source = "data.bankAccount.checkDigit", target = "dados.bankAccount.checkDigit")
    @Mapping(target = "dados.bankAccount.bankName", constant = BANK_NAME)
    ApiResponseData<AccountDataDtoResponse> toAccountDataDto(BackendResponseData<AccountDataEntityResponse> accountData);
}
