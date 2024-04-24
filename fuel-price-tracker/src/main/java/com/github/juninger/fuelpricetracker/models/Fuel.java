package com.github.juninger.fuelpricetracker.models;

public class Fuel {
    private String name;
    private String price;
    private String lastUpdated;
    private FuelType type; // GASOLINE or DIESEL

    public Fuel(String name, String price, String lastUpdated, FuelType type) {
        this.name = name;
        this.price = price;
        this.lastUpdated = lastUpdated;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public FuelType getType() {
        return type;
    }

    @Override
    public String toString() {
        return String.format("Type: %s | Price: %s | Last updated: %s", name, price, lastUpdated);
    }
}