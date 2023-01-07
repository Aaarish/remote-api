package com.example.covidapi.controller;

import com.example.covidapi.remote_api.ApiResponse;
import com.example.covidapi.remote_api.ApiResponseWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class AppService {
    private final RestTemplate restTemplate;

    public ApiResponse getWeather(String cityName){
        ApiResponse apiResponse = new ApiResponse();

        String apiUrl = "http://api.weatherapi.com/v1/current.json?key=1ff3450ce0a64d1b8eb123930230501&q="
                +cityName + "&aqi=no";

        ApiResponseWrapper weather = null;

        try {
            weather = apiResponseMethod(apiUrl);
        } catch (RuntimeException e){
            throw new RuntimeException("Unable to connect to  remote api");
        }

        apiResponse.setCity(weather.getLocation().get("name"));
        apiResponse.setCountry(weather.getLocation().get("country"));

        return apiResponse;
    }

    public ApiResponseWrapper apiResponseMethod(String apiUrl){
        ApiResponseWrapper wrapper = restTemplate.getForObject(apiUrl, ApiResponseWrapper.class);
        log.info("Conneting to the remote api");
        return wrapper;
    }
}
