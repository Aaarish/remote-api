package com.example.weatherapi.controller;

import com.example.weatherapi.remote_api.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AppController {
    private final AppService appService;
    @Autowired
    public AppController(AppService appService) {
        this.appService = appService;
    }

    @GetMapping("/weather")
    public ResponseEntity<ApiResponse> getWeather(@RequestParam String cityName){
        return ResponseEntity.ok(appService.getWeather(cityName));
    }
}
