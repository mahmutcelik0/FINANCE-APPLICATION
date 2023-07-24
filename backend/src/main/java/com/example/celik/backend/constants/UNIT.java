package com.example.celik.backend.constants;

public enum UNIT {
    KG("KG"),
    G("G"),
    C("CENT"),
    DOLLAR("DOLLAR"),
    FON("FON"),
    EURO("EURO");

    private String unit;

    UNIT(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }
}

