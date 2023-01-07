package com.example.covidapi.controller;

import com.example.covidapi.remote_api.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AppController {
    private final AppService appService;

    @GetMapping("/weather")
    public ResponseEntity<ApiResponse> getWeather(@RequestParam String cityName){
        return ResponseEntity.ok(appService.getWeather(cityName));
    }
}
