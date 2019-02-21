package com.test.simplebudgetapi.model;

public enum Currency {
    USD("USD"),
    EUR("EUR"),
    GBP("GBP"),
    IRN("IRN"),
    AUD("AUD"),
    CAD("CAD"),
    SGD("SGD"),
    CHF("CHF"),
    MYR("MYR"),
    JPY("JPY"),
    CNY("CNY"),
    NZD("NZD");

    private String mCodeString;

    private Currency(String codeString) {
        mCodeString = codeString;
    }

    @Override
    public String toString() {
        return mCodeString;
    }

}
