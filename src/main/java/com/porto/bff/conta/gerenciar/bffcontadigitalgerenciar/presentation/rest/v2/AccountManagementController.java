package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v2;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.service.account.v2.AccountManagementService;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v2.dto.AccountBalanceDtoResponse;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v2.mapper.AccountManagementMapper;
import com.porto.experiencia.cliente.conta.digital.commons.web.model.ApiResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequiredArgsConstructor
public class AccountManagementController implements AccountManagementControllerOperations {
    private final  AccountManagementService service;
    private final AccountManagementMapper mapper;
    @Override
    public ResponseEntity<ApiResponseData<AccountBalanceDtoResponse>> getBalanceAccount(String cognitoToken, String xItauAuth, String accountId) {
        return ResponseEntity.ok(this.mapper.toDto(this.service.getBalanceAccount(xItauAuth,accountId)));
    }
}
