package com.example.weatherapi.remote_api;

import lombok.Data;

import java.util.HashMap;

@Data
public class ApiResponseWrapper {
    private HashMap<String, String> location;
    private HashMap<String, Object> current;
}
