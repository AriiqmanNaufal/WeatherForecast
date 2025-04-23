package com.example.weatherforecast;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class WeatherService {

    private final String API_KEY = "a86e5d046d96f8208373bc75cadc6d27";
    private final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";

    public WeatherResponse getWeather(String city) {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format("%s?q=%s&appid=%s&units=metric", BASE_URL, city, API_KEY);

        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
        Map<String, Object> body = response.getBody();

        WeatherResponse weather = new WeatherResponse();
        weather.setCity(city);

        List<Map<String, Object>> weatherList = (List<Map<String, Object>>) body.get("weather");
        Map<String, Object> weatherInfo = weatherList.get(0);
        weather.setDescription(weatherInfo.get("description").toString());

        Map<String, Object> mainInfo = (Map<String, Object>) body.get("main");
        weather.setTemperature(Double.parseDouble(mainInfo.get("temp").toString()));

        return weather;
    }
}
