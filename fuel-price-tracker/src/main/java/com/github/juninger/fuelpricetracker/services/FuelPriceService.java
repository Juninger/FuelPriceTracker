package com.github.juninger.fuelpricetracker.services;

import com.github.juninger.fuelpricetracker.models.Fuel;
import com.github.juninger.fuelpricetracker.models.FuelType;
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

        List<GasStation> gasStations;

        // call scraper for each gas station and add to list

        GasStation preem = scraper.scrapePreem();

        GasStation[] circleK = scraper.scrapeCircleK();

        GasStation[] okq8 = scraper.scrapeOKQ8();

        GasStation ingo = scraper.scrapeIngo();

        gasStations = new ArrayList<>() {
            {
                add(preem);
                add(circleK[0]);
                add(circleK[1]);
                add(okq8[0]);
                add(okq8[1]);
                add(ingo);
            }
        };

        return gasStations;
    }

    // returns:
    // [0] --> cheapest gasoline
    // [1] --> cheapest diesel
    public Fuel[] calculateCheapestFuels(List<GasStation> gasStations) {

        // variables to track the current cheapest fuels
        Fuel cheapestGasoline = null, cheapestDiesel = null;
        double gasolinePrice = Double.MAX_VALUE;
        double dieselPrice = Double.MAX_VALUE;

        // iterate all stations and fuels
        for (GasStation station : gasStations) {
            for (Fuel fuel : station.getFuels()) {

                // extract price from string and parse to double
                double fuelPrice = Double.parseDouble(fuel.getPrice().substring(0,5).replace(',', '.'));

                // compare current fuel to the cheapest and update variables
                if (fuel.getType() == FuelType.GASOLINE && fuelPrice < gasolinePrice) {
                    gasolinePrice = fuelPrice;
                    cheapestGasoline = fuel;
                } else if (fuel.getType() == FuelType.DIESEL && fuelPrice < dieselPrice) {
                    dieselPrice = fuelPrice;
                    cheapestDiesel = fuel;
                }
            }
        }
        // [0] --> cheapest gasoline
        // [1] --> cheapest diesel
        return new Fuel[]{cheapestGasoline, cheapestDiesel};
    }
}
