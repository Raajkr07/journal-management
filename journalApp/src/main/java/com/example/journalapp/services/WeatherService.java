package com.example.journalapp.services;

import com.example.journalapp.apirespone.WeatherResponse;
import com.example.journalapp.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Autowired
    private AppCache appCache;

    @Value("${weather.api.key}")
    private String apiKey;
//    private static final String apiKey = "API_KEY_HERE";

    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeather(String city) {
        String replacedUrl = appCache.APP_CACHE.get("weather_api").replace("<city>", city).replace("<apikey>", apiKey);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(replacedUrl, HttpMethod.GET, null, WeatherResponse.class);
        return response.getBody();
    }
}
