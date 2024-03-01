package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.common.utils.v2;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.springframework.cloud.openfeign.security.OAuth2AccessTokenInterceptor.BEARER;


@UtilityClass
public class HttpUtils {

    public static final String PROVIDER = "IAAS";

    public static String includeBearerTokenPrefix(String xItauAuth) {
        var prefix = BEARER.concat(SPACE);
        xItauAuth = (Objects.isNull(xItauAuth)) ? EMPTY : xItauAuth;
        return (StringUtils.startsWith(xItauAuth, prefix)) ? xItauAuth : prefix.concat(xItauAuth);
    }
}
