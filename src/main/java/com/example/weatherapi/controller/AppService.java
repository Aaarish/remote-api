package com.example.weatherapi.controller;

import com.example.weatherapi.remote_api.ApiResponse;
import com.example.weatherapi.remote_api.ApiResponseWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class AppService {
    private final RestTemplate restTemplate;
    private final Logger LOG = LoggerFactory.getLogger(AppService.class);

    @Value("${api.base.url}")
    private String apiBaseUrl;

    @Value("${api.key}")
    private String apiKey;

    @Cacheable(value = "weatherCache", key = "#cityName")
    public ApiResponse getWeather(String cityName){
        ApiResponse apiResponse = new ApiResponse();

        String apiUrl = apiBaseUrl + "?key=" + apiKey + "&q=" + cityName + "&aqi=no";

        LOG.info("Executing without cache");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        ApiResponseWrapper weather = restTemplate.getForObject(apiUrl, ApiResponseWrapper.class);

        apiResponse.setCity(weather.getLocation().get("name"));
        apiResponse.setCountry(weather.getLocation().get("country"));
        apiResponse.setTemp((Double) weather.getCurrent().get("temp_c"));

        return apiResponse;
    }
}
