package com.example.celik.backend.constants;

public enum LOCATION {
    BANK("BANK"),
    REEL("REEL");

    private String location;

    LOCATION(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }
}
