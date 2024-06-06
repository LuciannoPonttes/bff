package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v2.mapper;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.BackendResponseData;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.account.balance.v2.AccountBalanceEntityResponse;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.account.data.v2.AccountDataEntityResponse;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.account.sumary.v2.AccountSummaryEntityResponse;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v2.dto.AccountBalanceDtoResponse;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v2.dto.AccountDataDtoResponse;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v2.dto.AccountSummaryDtoResponse;
import com.porto.experiencia.cliente.conta.digital.commons.web.model.ApiResponseData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

import static org.apache.commons.lang3.StringUtils.SPACE;

@Mapper(componentModel = "spring")
public interface AccountManagementMapper {

    String BANK_NAME = "Porto Bank -  Itaú Unibanco";

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

    @Named("buildBank")
    default String buildBank(String bankCode) {
       return Objects.isNull(bankCode) ? "341" : bankCode;
    }

    @Mapping(source = "data", target = "dados")
    ApiResponseData<AccountDataDtoResponse> toAccountDataDto(BackendResponseData<AccountDataEntityResponse> accountData);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "type", target = "type")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    @Mapping(source = "bankAccount.bank", target = "bankAccount.bank", qualifiedByName = "buildBank")
    @Mapping(source = "bankAccount.branch", target = "bankAccount.branch")
    @Mapping(source = "bankAccount.number", target = "bankAccount.number")
    @Mapping(source = "bankAccount.checkDigit", target = "bankAccount.checkDigit")
    @Mapping(target = "bankAccount.bankName", constant = BANK_NAME)
    AccountDataDtoResponse toAccountDataDto(AccountDataEntityResponse accountData);

    @Mapping(source = "data", target = "dados")
    ApiResponseData<AccountSummaryDtoResponse> toSummaryDto(BackendResponseData<AccountSummaryEntityResponse> summaryData);

    @Mapping(constant = BANK_NAME, target = "bankName")
    @Mapping(source = "document", target = "document")
    @Mapping(source = "account.bankAccount.bank", target = "bank", qualifiedByName = "buildBank")
    @Mapping(source = "account.bankAccount.number", target = "number")
    @Mapping(source = "account.bankAccount.checkDigit", target = "checkDigit")
    @Mapping(source = "account.bankAccount.branch", target = "branch")
    @Mapping(source = "account.status", target = "status")
    @Mapping(source = "balance.available", target = "balance", qualifiedByName = "buildBalance")
    @Mapping(source = "hasPortoCard", target = "hasPortoCard")
    @Mapping(source = "pixKeyCount", target = "pixKeyCount")
    AccountSummaryDtoResponse toSummaryDto(AccountSummaryEntityResponse summaryData);
}
