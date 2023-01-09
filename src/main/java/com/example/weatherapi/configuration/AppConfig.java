package com.example.weatherapi.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Arrays;

@Configuration
@EnableCaching
public class AppConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder
                .setConnectTimeout(Duration.ofSeconds(60))
                .setReadTimeout(Duration.ofSeconds(60))
                .build();
    }

    @Bean
    public KeyGenerator multiplyKeyGenerator() {
        return (Object target, Method method, Object... params) -> method.getName() + "_" + Arrays.toString(params);
    }
}
