package com.porto.bff.conta.timeline.bffcontastimeline.common.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ConstantUtils {

    // User-Agents
    public static final String USER_AGENT_ANDROID = "Android";
    public static final String USER_AGENT_IOS = "iOS";

    // Escopos
    public static final String ESCOPOS_DEFAULT = "iaas-billpayments.read iaas-billpayments.write iaas-qrcode-manager.read iaas-key-manager.write iaas-key-manager.read  iaas-recharge.write iaas-recharge.read iaas-pix-transaction.read iaas-pix-transaction.write iaas-receivables.write iaas-receivables.read iaas-onboarding.read iaas-kyc.write iaas-kyc.read iaas-contacts.write iaas-contacts.read iaas-automaticincome.read iaas-users.read iaas-timeline.read iaas-accounts.read iaas-accounts.write iaas-limits.read iaas-limits.write iaas-bank-statement.read iaas-externalcashout.write";
    public static final String IAAS_ONBOARDING_WRITE = "iaas-onboarding.write";
    public static final String CONSULTIVO = "consultivo";
    public static final String TRANSACIONAL = "transacional";
    public static final String CLIENTID_CONSULTIVO = "dc-vsblsio02i1k13uugcky477br";
    public static final String CLIENTID_TRANSACIONAL = "dc-pgrp0xb5n4phzywu7m06c9q4y";

}
