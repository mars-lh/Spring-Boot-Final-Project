package com.project.java.project.springboot.model.enums;

public enum ClaimIdentifier {


    AUTHORITIES("ROLE_TRAVELLER", "ROLE_ADMIN");

    private final String[] values;

    ClaimIdentifier(String... values) {
        this.values = values;
    }

    public String[] getValues() {
        return values;
    }
}


