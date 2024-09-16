package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.config;

import lombok.experimental.UtilityClass;
import org.slf4j.MDC;

import java.util.Map;
import java.util.function.Supplier;

@UtilityClass
public class LogConfig {

    public static <U> Supplier<U> withMdc(Supplier<U> supplier) {
        Map<String, String> mdc = MDC.getCopyOfContextMap();
        return () -> {
            MDC.setContextMap(mdc);
            return supplier.get();
        };
    }
}
