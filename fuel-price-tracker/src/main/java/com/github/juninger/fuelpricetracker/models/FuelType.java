package com.github.juninger.fuelpricetracker.models;

import java.util.Arrays;
import java.util.List;

public enum FuelType {
    GASOLINE,
    DIESEL;

    // used to simplify exposure of enum values to Thymeleaf
    public static List<FuelType> getAllFuelTypes() {
        return Arrays.asList(FuelType.values());
    }
}

