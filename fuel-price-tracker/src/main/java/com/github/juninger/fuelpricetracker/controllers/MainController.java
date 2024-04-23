package com.github.juninger.fuelpricetracker.controllers;

import com.github.juninger.fuelpricetracker.services.FuelPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    //TODO: Remove live reload / spring boot dev tools

    @Autowired
    private FuelPriceService fuelPriceService;

    @GetMapping("/")
    public String home(Model model) {

        // passing message to Thymeleaf frontend
        model.addAttribute("message", "Test-message to Thymeleaf");

        return "index"; // name of html file for homepage
    }
}
