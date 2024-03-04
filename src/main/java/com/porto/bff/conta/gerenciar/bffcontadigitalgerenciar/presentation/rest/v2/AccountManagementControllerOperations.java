package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v2;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.common.utils.ApiDocsConstants;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.common.utils.v2.HttpUtils;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v1.contas.dto.ContaResponseDto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v1.contas.dto.DadosResponseDto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v1.exception.ResponseErrorApi;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v2.dto.AccountBalanceDtoResponse;
import com.porto.experiencia.cliente.conta.digital.commons.web.model.ApiResponseData;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
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
@OpenAPIDefinition(
        info = @Info(
                title = ApiDocsConstants.API_DOC_TITLE,
                description = ApiDocsConstants.API_DOC_DESCRICAO
        )
)
public interface AccountManagementControllerOperations {
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
    @GetMapping("/saldo")
    ResponseEntity<ApiResponseData<AccountBalanceDtoResponse>> getBalanceAccount(@RequestHeader(value = AUTHORIZATION) String cognitoToken,
                                                                                 @RequestHeader(value = HttpUtils.HTTP_X_ITAU_AUTH_HEADER, required = false) String xItauAuth,
                                                                                 @RequestHeader(value = HttpUtils.HTTP_ACCOUNT_ID_HEADER, required = false) String accountId);

    @Operation(
            summary = "Consulta de dados da Conta Digital Porto",
            description = "para acessar tem que ter o escopo tipo iaas-accounts.read",
            tags = { "Gerenciar-Conta-Digital-V2" }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "400", description = "Falha na requisição", content =
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
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content =
                    {
                            @Content(mediaType = "application/json", schema =
                            @Schema(implementation = ResponseErrorApi.class))
                    })
    })
    @GetMapping("/dados-conta")
    ResponseEntity<DadosResponseDto<ContaResponseDto>> getAccountData(@RequestHeader(value = AUTHORIZATION) String cognitoToken,
                                                                      @RequestHeader(value = HttpUtils.HTTP_X_ITAU_AUTH_HEADER, required = false) String xItauAuth,
                                                                      @RequestHeader(value = HttpUtils.HTTP_ACCOUNT_ID_HEADER, required = false) String accountId);
}
