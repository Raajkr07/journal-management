package com.example.journalapp.services;

import com.example.journalapp.apirespone.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;
//    private static final String apiKey = "API_KEY_HERE";

    @Value("${weather.api.url}")
    private String apiUrl;
    //    private static final String url = "http://api.weatherstack.com/current?access_key=YOUR_ACCESS_KEY&query=CITY";

    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeather(String city) {
        String replacedUrl = apiUrl.replace("CITY", city).replace("YOUR_ACCESS_KEY", apiKey);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(replacedUrl, HttpMethod.GET, null, WeatherResponse.class);
        return response.getBody();
    }
}
