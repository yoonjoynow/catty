package org.catty.domains.account.model;

public enum CountryCode {

    KOREA("+81");

    private String value;

    CountryCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
