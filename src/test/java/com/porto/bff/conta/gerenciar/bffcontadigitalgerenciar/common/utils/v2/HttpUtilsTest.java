package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.common.utils.v2;

import org.junit.jupiter.api.Test;

import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.cloud.openfeign.security.OAuth2AccessTokenInterceptor.BEARER;

class HttpUtilsTest {

    @Test
    void testConstants(){
        assertEquals("IAAS", HttpUtils.HTTP_PROVIDER_VALUE);
    }

    @Test
    void includeBearerTokenPrefix() {
        assertEquals(BEARER.concat(SPACE), HttpUtils.includeBearerTokenPrefix(""));
        assertEquals(BEARER.concat(SPACE), HttpUtils.includeBearerTokenPrefix(null));
    }
}