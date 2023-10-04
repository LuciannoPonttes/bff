package com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.controller;

import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto.ContaResponseDto;
import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto.DadosResponseDto;
import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto.SaldoResponseDto;
import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto.SumarioResponseDto;
import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.exception.ResponseErrorApi;
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

import static com.porto.bff.conta.timeline.bffcontastimeline.common.utils.ApiDocsConstants.API_DOC_DESCRICAO;
import static com.porto.bff.conta.timeline.bffcontastimeline.common.utils.ApiDocsConstants.API_DOC_TITLE;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;



@RequestMapping("v3/conta-digital/gerenciar")
@OpenAPIDefinition(
        info = @Info(
                title = API_DOC_TITLE,
                description = API_DOC_DESCRICAO
        )
)
public interface GerenciamentoContasControllerOperations {


    @Operation(
            summary = "Consulta de dados da conta",
            description = "para acessar tem que ter o escopo tipo iaas-accounts.read",
            tags = { "Gerenciar" }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "400", description = "Parâmetros obrigatórios não enviados", content =
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
    ResponseEntity<DadosResponseDto<ContaResponseDto>> dadosConta(@RequestHeader(value = AUTHORIZATION) String tokenCognito,
                                                                   @RequestHeader(value = "x-itau-auth", required = false) String xItauAuth,
                                                                   @RequestHeader(value = "x-account-id", required = false) String contaId);




    @Operation(
            summary = "Consulta de dados da conta",
            description = "para acessar tem que ter o escopo tipo iaas-accounts.read",
            tags = { "Gerenciar" }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "400", description = "Parâmetros obrigatórios não enviados", content =
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
    @GetMapping("/saldo")
    ResponseEntity<DadosResponseDto<SaldoResponseDto>> saldoConta(@RequestHeader(value = AUTHORIZATION) String tokenCognito,
                                                                  @RequestHeader(value = "x-itau-auth", required = false) String xItauAuth,
                                                                  @RequestHeader(value = "x-account-id", required = false) String contaId);



    @Operation(
            summary = "Consulta do sumario",
            description = "para acessar tem que ter o escopo tipo iaas-accounts.read",
            tags = { "Gerenciar" }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "400", description = "Parâmetros obrigatórios não enviados", content =
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
    @GetMapping("/sumario")
    ResponseEntity<DadosResponseDto<SumarioResponseDto>> sumarioConta(@RequestHeader(value = AUTHORIZATION) String tokenCognito,
                                                                      @RequestHeader(value = "x-itau-auth", required = false) String xItauAuth,
                                                                      @RequestHeader(value = "x-account-id", required = false) String contaId);


/*


    @Operation(
            summary = "Atualização de status da conta",
            description = "para acessar tem que ter o escopo tipo iaas-accounts.write",
            tags = { "Gerenciar" }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "400", description = "Parâmetros obrigatórios não enviados", content =
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
    @PatchMapping("/conta")
    ResponseEntity<Void> ediatarStatusConta(@RequestHeader(AUTHORIZATION) String tokenCognito,
                                                               @RequestHeader("x-itau-auth") String xItauAuth,
                                                               @RequestHeader("x-account-id") String contaId,
                                                               @RequestBody ContaRequestDto requestDto);

    @Operation(
            summary = "Encerramento da conta",
            description = "para acessar tem que ter o escopo tipo iaas-accounts.write",
            tags = { "Gerenciar" }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "400", description = "Parâmetros obrigatórios não enviados", content =
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
    @DeleteMapping("/conta")
    ResponseEntity<Void> apagarConta(@RequestHeader(AUTHORIZATION) String tokenCognito,
                                     @RequestHeader("x-itau-auth") String xItauAuth,
                                     @RequestHeader("x-account-id") String contaId,
    @RequestBody RequestDeleteDto request);


 */
}
