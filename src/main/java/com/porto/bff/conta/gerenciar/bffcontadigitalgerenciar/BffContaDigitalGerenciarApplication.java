package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar;

import com.porto.experiencia.cliente.conta.digital.commons.feign.client.EnableContaDigitalFeignClient;
import com.porto.experiencia.cliente.conta.digital.commons.openapi.EnableContaDigitalOpenApi;
import com.porto.experiencia.cliente.conta.digital.commons.web.EnableBffContaDigitalWebHandler;
import com.porto.experiencia.cliente.conta.digital.commons.web.exceptionhandler.EnableBffContaDigitalErrorHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@EnableContaDigitalOpenApi
@EnableBffContaDigitalWebHandler
@EnableBffContaDigitalErrorHandler
@EnableContaDigitalFeignClient
@SpringBootApplication
@EnableRetry
public class BffContaDigitalGerenciarApplication {

    public static void main(String[] args) {
        SpringApplication.run(BffContaDigitalGerenciarApplication.class, args);
    }

}
