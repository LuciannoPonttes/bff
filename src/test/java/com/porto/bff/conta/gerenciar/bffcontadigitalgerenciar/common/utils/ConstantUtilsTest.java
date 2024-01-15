package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.common.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConstantUtilsTest {


    @Test
    public void testUserAgentsConstants() {
        assertEquals("Android", ConstantUtils.USER_AGENT_ANDROID);
        assertEquals("iOS", ConstantUtils.USER_AGENT_IOS);
    }

    @Test
    public void testEscoposConstants() {
        assertEquals("iaas-billpayments.read iaas-billpayments.write iaas-qrcode-manager.read iaas-key-manager.write iaas-key-manager.read  iaas-recharge.write iaas-recharge.read iaas-pix-transaction.read iaas-pix-transaction.write iaas-receivables.write iaas-receivables.read iaas-onboarding.read iaas-kyc.write iaas-kyc.read iaas-contacts.write iaas-contacts.read iaas-automaticincome.read iaas-users.read iaas-timeline.read iaas-accounts.read iaas-accounts.write iaas-limits.read iaas-limits.write iaas-bank-statement.read iaas-externalcashout.write",
                ConstantUtils.ESCOPOS_DEFAULT);
        assertEquals("iaas-onboarding.write", ConstantUtils.IAAS_ONBOARDING_WRITE);
        assertEquals("consultivo", ConstantUtils.CONSULTIVO);
        assertEquals("transacional", ConstantUtils.TRANSACIONAL);
        assertEquals("dc-vsblsio02i1k13uugcky477br", ConstantUtils.CLIENTID_CONSULTIVO);
        assertEquals("dc-pgrp0xb5n4phzywu7m06c9q4y", ConstantUtils.CLIENTID_TRANSACIONAL);
    }
}