package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v2;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application_old.service.account.v2.AccountManagementService;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v2.dto.AccountBalanceDtoResponse;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v2.dto.AccountDataDtoResponse;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v2.dto.AccountSummaryDtoResponse;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v2.mapper.AccountManagementMapper;
import com.porto.experiencia.cliente.conta.digital.commons.web.model.ApiResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountManagementController implements AccountManagementOperations {
    private final AccountManagementService service;
    private final AccountManagementMapper mapper;

    @Override
    public ResponseEntity<ApiResponseData<AccountBalanceDtoResponse>> getBalanceAccount(String cognitoToken, String xItauAuth, String accountId) {
        return ResponseEntity.ok(this.mapper.toAccountBalanceDto(this.service.getBalanceAccount(xItauAuth, accountId)));
    }

    @Override
    public ResponseEntity<ApiResponseData<AccountDataDtoResponse>> getAccountData(String cognitoToken, String xItauAuth, String accountId) {
        return ResponseEntity.ok(this.mapper.toAccountDataDto(this.service.getAccountData(xItauAuth, accountId)));
    }

    @Override
    public ResponseEntity<ApiResponseData<AccountSummaryDtoResponse>> getSummaryAccount(String cognitoToken, String xItauAuth, String accountId) {
        return ResponseEntity.ok(this.mapper.toSummaryDto(this.service.getSummaryAccount(cognitoToken, xItauAuth, accountId)));
    }
}
