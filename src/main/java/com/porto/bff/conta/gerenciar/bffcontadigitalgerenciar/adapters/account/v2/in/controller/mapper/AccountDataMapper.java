package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.account.v2.in.controller.mapper;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.account.v2.in.controller.response.dto.AccountDataResponseDto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.account.AccountDataEntityResponseDomain;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.backend.BackendResponseDataDomain;
import com.porto.experiencia.cliente.conta.digital.commons.web.model.ApiResponseData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import java.util.Objects;

@Mapper(componentModel = "spring")
public interface AccountDataMapper {
    String BANK_NAME = "Porto Bank - Itaú Unibanco";

    @Named("buildBank")
    default String buildBank(String bankCode) {
        return Objects.isNull(bankCode) ? "341" : bankCode;
    }

    @Mapping(source = "data", target = "dados")
    ApiResponseData<AccountDataResponseDto> toAccountDataDto(BackendResponseDataDomain<AccountDataEntityResponseDomain> accountData);


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
    AccountDataResponseDto toAccountDataDto(AccountDataEntityResponseDomain accountData);

}
