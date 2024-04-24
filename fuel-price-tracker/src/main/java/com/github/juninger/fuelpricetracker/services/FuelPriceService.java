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
}
