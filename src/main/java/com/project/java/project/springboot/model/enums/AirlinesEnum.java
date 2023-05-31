package com.project.java.project.springboot.model.enums;


public enum AirlinesEnum {

    LH("Lufthansa"),
    OS("Austrian Airlines"),
    LX("Swiss International Air Lines"),
    EW("Eurowings");


    private final String description;

    AirlinesEnum(String description) {
        this.description = description;
    }

    public String getCode() {
        return this.name();
    }

    public String getDescription() {
        return description;
    }
}
