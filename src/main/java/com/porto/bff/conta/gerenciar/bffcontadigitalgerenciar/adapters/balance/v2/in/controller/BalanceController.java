package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.balance.v2.in.controller;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.balance.v2.in.controller.mapper.BalanceMapper;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.balance.v2.in.controller.response.dto.BalanceDtoResponse;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.ports.balance.in.BalanceInputPort;
import com.porto.experiencia.cliente.conta.digital.commons.web.model.ApiResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BalanceController implements BalanceOperations {

    private final BalanceInputPort balanceInputPort;
    private final BalanceMapper balanceMapper;

    @Override
    public ResponseEntity<ApiResponseData<BalanceDtoResponse>> getBalanceAccount(String cognitoToken, String xItauAuth, String accountId) {
      return ResponseEntity.ok(this. balanceMapper.toAccountBalanceDto(this.balanceInputPort.getBalanceAccount(xItauAuth, accountId)));
    }

}
