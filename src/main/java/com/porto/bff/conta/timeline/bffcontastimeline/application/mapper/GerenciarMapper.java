package com.porto.bff.conta.timeline.bffcontastimeline.application.mapper;

import com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.conta.response.AccountResponseIaasPorto;
import com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.conta.response.DataResponseIassPorto;
import com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.conta.response.saldo.Balance;
import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto.ContaResponseDto;
import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto.ContaSaldoResponseDto;
import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto.ContaSumarioResponseDto;
import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto.DadosResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface GerenciarMapper {

    @Mapping(source = "data", target = "dados")
    DadosResponseDto<ContaResponseDto> converteRespostaContaIaas(DataResponseIassPorto<AccountResponseIaasPorto> conta);

    @Mappings({
            @Mapping(source = "account.id", target = "id"),
            @Mapping(source = "account.bankAccount.bank", target = "contaBancaria.banco"),
            @Mapping(source = "account.bankAccount.branch", target = "contaBancaria.agencia"),
            @Mapping(source = "account.bankAccount.number", target = "contaBancaria.numero"),
            @Mapping(source = "account.bankAccount.checkDigit", target = "contaBancaria.digitoConta"),
            @Mapping(source = "status", target = "status"),
            @Mapping(source = "type", target = "tipo"),
            @Mapping(source = "createdAt", target = "criado"),
            @Mapping(source = "updatedAt", target = "atualizado")

    })
    ContaResponseDto paraContaResponseDto(AccountResponseIaasPorto account);

    @Mapping(source = "data", target = "dados")
    DadosResponseDto<ContaSaldoResponseDto> converteRespostaContaSaldoIaas(DataResponseIassPorto<Balance> balance);



//    @Mapping(source = "data", target = "dados")
//    DadosResponseDto<ContaSumarioResponseDto> converteRespostaSumarioIaas(DataResponseIassPorto<AccountResponseIaasPorto> conta,
//                                                                          DataResponseIassPorto<Balance> saldo);
}
