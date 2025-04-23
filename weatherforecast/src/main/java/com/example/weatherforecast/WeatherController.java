package com.example.weatherforecast;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/weather")
    public String getWeather(@RequestParam("city") String city, Model model) {
        try {
            WeatherResponse weather = weatherService.getWeather(city);
            model.addAttribute("weather", weather);
            return "result";  // Make sure result.html exists in src/main/resources/templates
        } catch (Exception e) {
            model.addAttribute("error", "Could not retrieve weather data. Please check the city name.");
            e.printStackTrace(); // Log the actual error to the console
            return "index";  // Redirect back to the index page with an error message
        }
    }
}

