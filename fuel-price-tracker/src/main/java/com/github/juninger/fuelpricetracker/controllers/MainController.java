package com.github.juninger.fuelpricetracker.controllers;

import com.github.juninger.fuelpricetracker.models.Fuel;
import com.github.juninger.fuelpricetracker.models.FuelType;
import com.github.juninger.fuelpricetracker.models.GasStation;
import com.github.juninger.fuelpricetracker.services.DateTimeService;
import com.github.juninger.fuelpricetracker.services.FuelPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private FuelPriceService fuelPriceService;

    @Autowired
    private DateTimeService dateTimeService;

    @GetMapping("/")
    public String home(Model model) {

        //TODO: Remove live reload / spring boot dev tools
        //TODO: Consider using Thymeleaf fragments for table

        // calls service --> scraper to fetch all data
        List<GasStation> gasStations = fuelPriceService.getAllFuelPrices();

        // pass list of all fuels to find the cheapest ones (both gasoline & diesel)
        Fuel[] cheapestFuels = fuelPriceService.calculateCheapestFuels(gasStations);
        Fuel cheapestGasoline = cheapestFuels[0];
        Fuel cheapestDiesel = cheapestFuels[1];

        // pass necessary attributes to Thymeleaf frontend
        model.addAttribute("fuelTypes", FuelType.getAllFuelTypes());
        // current date & time to display time of update
        model.addAttribute("dateTime", dateTimeService.getCurrentDateTime());
        model.addAttribute("gasStations", gasStations);
        model.addAttribute("cheapestGasoline", cheapestGasoline.getPrice());
        model.addAttribute("cheapestDiesel", cheapestDiesel.getPrice());

        return "index"; // name of html file for homepage
    }
}
