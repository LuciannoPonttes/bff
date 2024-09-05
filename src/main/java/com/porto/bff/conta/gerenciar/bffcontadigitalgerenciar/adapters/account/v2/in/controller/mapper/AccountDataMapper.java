package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.account.v2.in.controller.mapper;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.BackendResponseData;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.account.data.v2.AccountDataEntityResponse;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v2.dto.AccountDataDtoResponse;
import com.porto.experiencia.cliente.conta.digital.commons.web.model.ApiResponseData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Objects;

import static com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v2.mapper.AccountManagementMapper.BANK_NAME;

@Mapper(componentModel = "spring")
public interface AccountDataMapper {

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

}
