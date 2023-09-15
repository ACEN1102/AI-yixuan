package com.digitalchina.aiyixuan.controller;

import com.digitalchina.aiyixuan.model.DTO.CityInfo;
import com.digitalchina.aiyixuan.model.DTO.WeatherDTO;
import com.digitalchina.aiyixuan.service.Impl.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/weather")
public class WeatherController {
    @Resource
    private WeatherService weatherService;

    @GetMapping("/byIp")
    public WeatherDTO getWeatherByIp(@RequestParam(required = false) String ip) {
        String city = weatherService.getCityByIp(ip);
        return weatherService.getWeather(city);
    }

    @GetMapping("/city")
    public String getCityName(@RequestParam(required = false) String ip) {
        return weatherService.getCityByIp(ip);
    }

}
