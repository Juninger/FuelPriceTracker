package com.github.juninger.fuelpricetracker.controllers;

import com.github.juninger.fuelpricetracker.models.FuelType;
import com.github.juninger.fuelpricetracker.models.GasStation;
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

    @GetMapping("/")
    public String home(Model model) {

        List<GasStation> gasStations = fuelPriceService.getAllFuelPrices();

        //TODO: Remove live reload / spring boot dev tools
        //TODO: Consider using Thymeleaf fragments for table
        //TODO: Add search + sort
        //TODO: Add styling library

        model.addAttribute("gasStations", gasStations);
        model.addAttribute("fuelTypes", FuelType.getAllFuelTypes());

        return "index"; // name of html file for homepage
    }
}
