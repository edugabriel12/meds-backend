package com.br.medsbackend.core.enumeration;

public enum Gender {
    MASCULINO("Homem"),
    FEMININO("Mulher"),
    OUTRO("Outro");

    private String name;

    Gender(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
