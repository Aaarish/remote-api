package com.example.covidapi.controller;

import com.example.covidapi.remote_api.ApiResponse;
import com.example.covidapi.remote_api.ApiResponseWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class AppService {
    private final RestTemplate restTemplate;

    @Value("${api.base.url}")
    private String apiBaseUrl;

    @Value("${api.key}")
    private String apiKey;

    public ApiResponse getWeather(String cityName){
        ApiResponse apiResponse = new ApiResponse();

        String apiUrl = apiBaseUrl + "?key=" + apiKey + "&q=" + cityName + "&aqi=no";

        ApiResponseWrapper weather = apiResponseMethod(apiUrl);

        apiResponse.setCity(weather.getLocation().get("name"));
        apiResponse.setCountry(weather.getLocation().get("country"));
        apiResponse.setTemp((Double) weather.getCurrent().get("temp_c"));

        return apiResponse;
    }

    public ApiResponseWrapper apiResponseMethod(String apiUrl){
        ApiResponseWrapper wrapper = restTemplate.getForObject(apiUrl, ApiResponseWrapper.class);
        log.info("Conneting to the remote api");
        return wrapper;
    }
}
