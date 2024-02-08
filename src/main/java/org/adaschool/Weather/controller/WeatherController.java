package org.adaschool.Weather.controller;

import org.adaschool.Weather.data.WeatherReport;
import org.adaschool.Weather.service.WeatherReportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class WeatherController {
    private final WeatherReportService weatherReportService;

    public WeatherController(WeatherReportService weatherReportService) {
        this.weatherReportService = weatherReportService;
    }

    @GetMapping("/weather-report")
    public WeatherReport getWeatherReport(@RequestParam double latitude, @RequestParam double longitude) {
        return weatherReportService.getWeatherReport(latitude, longitude);
    }
}
