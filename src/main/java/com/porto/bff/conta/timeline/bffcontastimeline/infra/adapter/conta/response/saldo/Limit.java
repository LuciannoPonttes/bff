package com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.conta.response.saldo;

public class Limit {

    private String id;
    private int availableAmount;
    private int customerChosenAmount;
    private int minDefaultValue;
    private int maxDefaultValue;
    private String transactionProductType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(int availableAmount) {
        this.availableAmount = availableAmount;
    }

    public int getCustomerChosenAmount() {
        return customerChosenAmount;
    }

    public void setCustomerChosenAmount(int customerChosenAmount) {
        this.customerChosenAmount = customerChosenAmount;
    }

    public int getMinDefaultValue() {
        return minDefaultValue;
    }

    public void setMinDefaultValue(int minDefaultValue) {
        this.minDefaultValue = minDefaultValue;
    }

    public int getMaxDefaultValue() {
        return maxDefaultValue;
    }

    public void setMaxDefaultValue(int maxDefaultValue) {
        this.maxDefaultValue = maxDefaultValue;
    }

    public String getTransactionProductType() {
        return transactionProductType;
    }

    public void setTransactionProductType(String transactionProductType) {
        this.transactionProductType = transactionProductType;
    }
}

