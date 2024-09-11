package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.summary.v2.in.controller.mapper;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.summary.v2.in.controller.response.dto.SummaryDtoResponse;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.summary.SummaryEntityResponseDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;
import static org.apache.commons.lang3.StringUtils.SPACE;

@Mapper(componentModel = "spring")
public interface SummaryMapper {

    String BANK_NAME = "Porto Bank - Itaú Unibanco";

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
    SummaryDtoResponse toSummaryDto(SummaryEntityResponseDomain summaryData);

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

}
