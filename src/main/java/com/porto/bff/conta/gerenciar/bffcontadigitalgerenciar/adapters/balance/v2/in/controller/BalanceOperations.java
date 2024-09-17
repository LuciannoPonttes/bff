package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.balance.v2.in.controller;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.balance.v2.in.controller.response.dto.BalanceDtoResponse;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.common.utils.v2.HttpUtils;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.common.ResponseErrorApi;
import com.porto.experiencia.cliente.conta.digital.commons.web.model.ApiResponseData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RequestMapping({"v2/conta-digital/gerenciar"})
public interface BalanceOperations {

    @Operation(
            summary = "Consulta de dados da conta",
            description = "para acessar tem que ter o escopo tipo iaas-accounts.read",
            tags = {"Gerenciar-Conta-Digital-V2"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "400", description = "Falha na Requisição", content =
                    {
                            @Content(mediaType = "application/json", schema =
                            @Schema(implementation = ResponseErrorApi.class))
                    }),
            @ApiResponse(responseCode = "404", description = "Conta não encontrada", content =
                    {
                            @Content(mediaType = "application/json", schema =
                            @Schema(implementation = ResponseErrorApi.class))
                    }),
            @ApiResponse(responseCode = "498", description = "Token inválido", content =
                    {
                            @Content(mediaType = "application/json", schema =
                            @Schema(implementation = ResponseErrorApi.class))
                    }),
            @ApiResponse(responseCode = "500", description = "Erro interno de Servidor", content =
                    {
                            @Content(mediaType = "application/json", schema =
                            @Schema(implementation = ResponseErrorApi.class))
                    })
    })
    @GetMapping("/hexa-saldo")
    ResponseEntity<ApiResponseData<BalanceDtoResponse>> getBalanceAccount(@RequestHeader(value = AUTHORIZATION) String cognitoToken,
                                                                          @RequestHeader(value = HttpUtils.HTTP_X_ITAU_AUTH_HEADER, required = false) String xItauAuth,
                                                                          @RequestHeader(value = HttpUtils.HTTP_ACCOUNT_ID_HEADER, required = false) String accountId);
}
