package com.porto.bff.conta.timeline.bffcontastimeline;

import com.porto.experiencia.cliente.cartoes.commons.openapi.EnableCartoesOpenApi;
import com.porto.experiencia.cliente.conta.digital.commons.web.EnableBffContaDigitalWebHandler;
import com.porto.experiencia.cliente.conta.digital.commons.web.exceptionhandler.EnableBffContaDigitalErrorHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableCartoesOpenApi
@EnableBffContaDigitalWebHandler
@EnableBffContaDigitalErrorHandler
@EnableFeignClients
@SpringBootApplication
public class BffContaDigitalGerenciarApplication {

    public static void main(String[] args) {
        SpringApplication.run(BffContaDigitalGerenciarApplication.class, args);
    }

}
