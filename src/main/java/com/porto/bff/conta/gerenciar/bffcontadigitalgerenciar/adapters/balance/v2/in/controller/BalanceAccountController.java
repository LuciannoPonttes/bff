package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.balance.v2.in.controller;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.balance.v2.in.controller.mapper.BalanceAccountMapper;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.ports.account.in.AccountDataInputPort;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v2.dto.AccountBalanceDtoResponse;
import com.porto.experiencia.cliente.conta.digital.commons.web.model.ApiResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BalanceAccountController implements BalanceAccountOperations {

    private final AccountDataInputPort accountDataInputPort;
    private final BalanceAccountMapper balanceMapper;

    @Override
    public ResponseEntity<ApiResponseData<AccountBalanceDtoResponse>> getBalanceAccount(String cognitoToken, String xItauAuth, String accountId) {
       return null;
        // return ResponseEntity.ok(this. balanceMapper.toAccountBalanceDto(this.service.getBalanceAccount(xItauAuth, accountId)));
    }

}
