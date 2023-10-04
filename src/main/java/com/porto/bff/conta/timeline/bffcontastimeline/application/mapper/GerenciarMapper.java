package com.porto.bff.conta.timeline.bffcontastimeline.application.mapper;

import com.porto.bff.conta.timeline.bffcontastimeline.domain.model.DataResponseIassPorto;
import com.porto.bff.conta.timeline.bffcontastimeline.domain.model.conta.AccountResponseIaasPorto;
import com.porto.bff.conta.timeline.bffcontastimeline.domain.model.saldo.BalanceResponseIaasPorto;
import com.porto.bff.conta.timeline.bffcontastimeline.domain.model.sumario.SumarioResponseIaasPorto;
import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto.ContaResponseDto;
import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto.DadosResponseDto;
import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto.SaldoResponseDto;
import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto.SumarioResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.text.NumberFormat;
import java.util.Locale;

import static org.apache.commons.lang3.StringUtils.SPACE;

@Mapper(componentModel = "spring")
public interface GerenciarMapper {

    @Mapping(source = "data", target = "dados")
    DadosResponseDto<ContaResponseDto> paraDadosContaResponseDto(DataResponseIassPorto<AccountResponseIaasPorto> conta);

    @Mappings({
            @Mapping(source = "account.id", target = "id"),
            @Mapping(source = "account.bankAccount.bank", target = "contaBancaria.ispb"),
            @Mapping(source = "account.bankAccount.branch", target = "contaBancaria.agencia"),
            @Mapping(source = "account.bankAccount.number", target = "contaBancaria.numero"),
            @Mapping(source = "account.bankAccount.checkDigit", target = "contaBancaria.digitoConta"),
            @Mapping(source = "status", target = "status"),
            @Mapping(source = "type", target = "tipo"),
            @Mapping(source = "createdAt", target = "criadoEm"),
            @Mapping(source = "updatedAt", target = "atualizadoEm")

    })
    ContaResponseDto paraContaResponseDto(AccountResponseIaasPorto account);

    @Mapping(source = "data", target = "dados")
    DadosResponseDto<SaldoResponseDto> paraDadosSaldoResponseDto(DataResponseIassPorto<BalanceResponseIaasPorto> balance);

    @Mappings({
            @Mapping(source = "accountId", target = "contaId"),
            @Mapping(source = "available", target = "disponivel", qualifiedByName = "mapSaldo"),
            @Mapping(source = "reserved", target = "reservada", qualifiedByName = "mapSaldo"),
            @Mapping(source = "blocked", target = "bloqueado", qualifiedByName = "mapSaldo")
    })
    SaldoResponseDto paraContaSaldoResponseDto(BalanceResponseIaasPorto balance);


    @Mapping(source = "data", target = "dados")
    DadosResponseDto<SumarioResponseDto> paraDadosSumarioResponseDto(DataResponseIassPorto<SumarioResponseIaasPorto> sumario);

    @Mappings({
            @Mapping(source = "sumario.account.data.bankAccount.bank", target = "ispb"),
            @Mapping(source = "sumario.account.data.bankAccount.branch", target = "agencia"),
            @Mapping(source = "sumario.account.data.bankAccount.number", target = "conta"),
            @Mapping(source = "sumario.account.data.bankAccount.checkDigit", target = "digitoConta"),
            @Mapping(source = "sumario.account.data.status", target = "statusConta"),
            @Mapping(source = "sumario.balance.data.available", target = "saldo", qualifiedByName = "mapSaldo"),
            @Mapping(source = "sumario.document", target = "documento")
    })
    SumarioResponseDto paraContaSumarioResponseDto(SumarioResponseIaasPorto sumario);

    @Named("mapSaldo")
    default String mapSaldo(Double saldo) {
        var br = new Locale("pt", "BR");
        var formatoMoeda = NumberFormat.getCurrencyInstance(br);
        return formatoMoeda.format(saldo).replaceAll("\\p{Z}",SPACE);
    }
}
