package com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.conta.client.teste;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperConfig {

    @Bean
    public ObjectMapperConfig objectMapper() {
        ObjectMapperConfig mapper = new ObjectMapperConfig();
        return mapper;
    }
}
