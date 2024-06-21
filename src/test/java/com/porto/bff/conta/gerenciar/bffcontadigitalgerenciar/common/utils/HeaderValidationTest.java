package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.common.utils;


import com.porto.experiencia.cliente.conta.digital.commons.domain.exception.BusinessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.function.Executable;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


import java.util.Optional;


class HeaderValidationTest {

  @InjectMocks
    HeaderValidation headerValidation;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testIsValidHeaderProjet_ValidInputs() {
        String validXItauAuth = "validAuth";
        String validContaId = "validContaId";
        assertDoesNotThrow(() -> headerValidation.isValidHeaderProjet(validXItauAuth, validContaId));
    }

    @Test
    public void testIsValidHeaderProjet_EmptyXItauAuth() {
        String emptyXItauAuth = "";
        String validContaId = "validContaId";
        Executable executable = () -> headerValidation.isValidHeaderProjet(emptyXItauAuth, validContaId);
        BusinessException exception = assertThrows(BusinessException.class, executable);
        assertEquals(498, Integer.parseInt(String.valueOf(exception.getStatusCode())));
        assertEquals("IAAS_EXPIRATION_TOKEN", exception.getCampo());
        assertEquals("AccessToken Inválido, gerar outro", exception.getDetalhe());
    }

    @Test
    public void testIsValidHeaderProjet_EmptyContaId() {
        String validXItauAuth = "validAuth";
        String emptyContaId = "";
        Executable executable = () -> headerValidation.isValidHeaderProjet(validXItauAuth, emptyContaId);
        BusinessException exception = assertThrows(BusinessException.class, executable);
        assertEquals(498, Integer.parseInt(String.valueOf(exception.getStatusCode())));
        assertEquals("IAAS_EXPIRATION_TOKEN", exception.getCampo());
        assertEquals("AccessToken Inválido, gerar outro", exception.getDetalhe());
    }

    @Test
    public void testIsValidHeaderProjet_BothInputsEmpty() {
        String emptyXItauAuth = "";
        String emptyContaId = "";
        Executable executable = () -> headerValidation.isValidHeaderProjet(emptyXItauAuth, emptyContaId);
        BusinessException exception = assertThrows(BusinessException.class, executable);
        assertEquals(498, Integer.parseInt(String.valueOf(exception.getStatusCode())));
        assertEquals("IAAS_EXPIRATION_TOKEN", exception.getCampo());
        assertEquals("AccessToken Inválido, gerar outro", exception.getDetalhe());
    }
}
