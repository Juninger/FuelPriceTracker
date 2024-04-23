package com.github.juninger.fuelpricetracker.services;

import com.github.juninger.fuelpricetracker.models.GasStation;
import com.github.juninger.fuelpricetracker.scrapers.FuelPriceScraper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FuelPriceService {

    private final FuelPriceScraper scraper;

    public FuelPriceService(FuelPriceScraper scraper) {
        this.scraper = scraper;
    }

    public List<GasStation> getAllFuelPrices() {
        List<GasStation> gasStations = new ArrayList<>();

        // call scraper for each gas station and add to list

        return gasStations;
    }
}
