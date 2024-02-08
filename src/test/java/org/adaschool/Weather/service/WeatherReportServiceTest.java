package org.adaschool.Weather.service;

import org.adaschool.Weather.data.WeatherReport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;


@ExtendWith(MockitoExtension.class)
public class WeatherReportServiceTest {
    @InjectMocks
    private WeatherReportService weatherReportService;
    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testGetWeatherReport(){
        WeatherReport expected=new WeatherReport();
        expected.setTemperature(0.0);
        expected.setHumidity(49.0);
        //Mockito.when(weatherReportService.getWeatherReport(14.64072,-90.51327)).thenReturn(expected);
        final WeatherReport result = weatherReportService.getWeatherReport(14.64072,-90.51327);

        Assertions.assertEquals(expected.getHumidity(),result.getHumidity());
        Assertions.assertEquals(expected.getTemperature(),result.getTemperature());
    }
}
