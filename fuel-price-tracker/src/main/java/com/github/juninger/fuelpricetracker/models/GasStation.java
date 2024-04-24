package com.github.juninger.fuelpricetracker.models;
import java.util.ArrayList;

public class GasStation {
    private String name;
    private ArrayList<Fuel> fuels = new ArrayList<>();

    public GasStation(String name) {
        this.name = name;
    }

    public void addNewFuel(Fuel fuel) {
        if (fuel != null) fuels.add(fuel);
    }

    public ArrayList<Fuel> getFuels() {
        return fuels;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + "\n" + fuels.toString();
    }
}