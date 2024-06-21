package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.common.utils;

import com.porto.experiencia.cliente.conta.digital.commons.domain.exception.BusinessException;
import lombok.Builder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class HeaderValidation {

    public void isValidHeaderProjet(String xItauAuth, String contaId) {
        if (StringUtils.isEmpty(xItauAuth) || StringUtils.isEmpty(contaId)) {
            throw new BusinessException(498, "IAAS_EXPIRATION_TOKEN", "AccessToken Inválido, gerar outro");
        }
    }
}
