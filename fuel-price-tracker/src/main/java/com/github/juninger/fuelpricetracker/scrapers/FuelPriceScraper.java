package com.github.juninger.fuelpricetracker.scrapers;

import com.github.juninger.fuelpricetracker.models.Fuel;
import com.github.juninger.fuelpricetracker.models.FuelType;
import com.github.juninger.fuelpricetracker.models.GasStation;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component // instantiated by Spring
public class FuelPriceScraper {

    public GasStation scrapeIngo() {

        GasStation ingo = new GasStation("Ingo");
        String URL = "https://www.ingo.se/v%C3%A5ra-l%C3%A5ga-priser/v%C3%A5ra-l%C3%A5ga-priser/aktuella-listpriser";

        try {
            // fetch HTML document
            Document doc = Jsoup.connect(URL).get();

            // select table for prices and extract rows
            Elements rows = doc.select("div.ck-prices-per-product tbody tr");

            for (Element row : rows) {

                Elements data = row.select("td"); // collection of table data cells in row
                String name = data.get(1).select("div").first().ownText();

                // filter to only scrape rows we are interested in and sort GASOLINE / DIESEL
                if (name.equals("Bensin 95") || name.equals("Bensin 98 E5")) {
                    ingo.addNewFuel(new Fuel(name,
                            data.get(2).select("div").first().ownText() + " kr/l", // price + units
                            data.get(3).select("time").text(), // last updated
                            FuelType.GASOLINE));
                } else if (name.equals("Diesel")) {
                    ingo.addNewFuel(new Fuel(name,
                            data.get(2).select("div").first().ownText() + " kr/l", // price + units
                            data.get(3).select("time").text(), // last updated
                            FuelType.DIESEL));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ingo;
    }

    public GasStation[] scrapeOKQ8() {
        /* Manned and unmanned prices are in the same table but in different columns
        *  GasStation[0] --> manned station prices
        *  GasStation[1] --> unmanned station prices */
        GasStation[] okq8 = new GasStation[]{new GasStation("OKQ8, manned"), new GasStation("OKQ8, unmanned")};
        String URL = "https://www.okq8.se/pa-stationen/drivmedel/";

        try {
            // fetch HTML document
            Document doc = Jsoup.connect(URL).get();

            // select table for prices and extract rows
            Elements rows = doc.select("table.table-clean tbody tr");

            for (Element row : rows) {

                Elements data = row.select("td"); // collection of table data cells in row
                String name = data.first().text();

                // filter to only scrape rows we are interested in and sort GASOLINE / DIESEL
                if (name.equals("GoEasy Bensin 95 (E10)") || name.equals("GoEasy Bensin Extra 98 (E5)")) {
                    okq8[0].addNewFuel(new Fuel(name, // manned station
                            data.get(1).text() + "/l", // price + units
                            data.get(4).text(), // last updated
                            FuelType.GASOLINE));
                    okq8[1].addNewFuel(new Fuel(name, // manned station
                            data.get(1).text() + "/l", // price + units
                            data.get(4).text(), // last updated
                            FuelType.GASOLINE));

                } else if (name.equals("GoEasy Diesel (B7)") || name.equals("GoEasy Diesel Extra (B0)") || name.equals("Neste MY Förnybar Diesel (HVO100)")) {
                    okq8[0].addNewFuel(new Fuel(name, // manned station
                            data.get(1).text() + "/l", // price + units
                            data.get(4).text(), // last updated
                            FuelType.DIESEL));
                    okq8[1].addNewFuel(new Fuel(name, // manned station
                            data.get(1).text() + "/l", // price + units
                            data.get(4).text(), // last updated
                            FuelType.DIESEL));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return okq8;
    }
}
