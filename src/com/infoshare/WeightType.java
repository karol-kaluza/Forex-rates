package com.infoshare;

public enum WeightType {
    LIGHT(" is too low"), MEDIUM(" You are perfect :)"), HEAVY(" sorry, you are FAT !!!");

    private final String description;

    WeightType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
