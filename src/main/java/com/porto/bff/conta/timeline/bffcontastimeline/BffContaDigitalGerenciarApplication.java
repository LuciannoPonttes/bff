package com.porto.bff.conta.timeline.bffcontastimeline;

import com.porto.experiencia.cliente.cartoes.commons.feign.client.EnableCartoesFeignClient;
import com.porto.experiencia.cliente.cartoes.commons.openapi.EnableCartoesOpenApi;
import com.porto.experiencia.cliente.cartoes.commons.web.EnableBffCartoesWebHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableCartoesOpenApi
@EnableBffCartoesWebHandler
@EnableFeignClients
@SpringBootApplication
public class BffContaDigitalGerenciarApplication {

	public static void main(String[] args) {
		SpringApplication.run(BffContaDigitalGerenciarApplication.class, args);
	}

}
