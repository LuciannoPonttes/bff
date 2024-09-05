package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.account.v2.in.controller;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.account.v2.in.controller.mapper.AccountDataMapper;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.account.v2.in.controller.response.dto.AccountDataResponseDto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.ports.account.in.AccountDataInputPort;
import com.porto.experiencia.cliente.conta.digital.commons.web.model.ApiResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountDataController implements AccountDataOperations {

    private final AccountDataInputPort accountDataInputPort;
    private final AccountDataMapper accountDataMapper;

    @Override
    public ResponseEntity<ApiResponseData<AccountDataResponseDto>> getAccountData(String cognitoToken, String xItauAuth, String accountId) {
        return ResponseEntity.ok(this.accountDataMapper.toAccountDataDto(this.accountDataInputPort.getAccountData(xItauAuth, accountId)));

    }

}
