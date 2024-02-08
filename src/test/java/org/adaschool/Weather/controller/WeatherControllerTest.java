package org.adaschool.Weather.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.adaschool.Weather.data.WeatherReport;
import org.adaschool.Weather.service.WeatherReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@WebMvcTest(WeatherController.class)
public class WeatherControllerTest {

    @Autowired
    private WeatherController weatherController;
    private MockMvc mockMvc;
    @MockBean
    private WeatherReportService weatherReportService;

    @BeforeEach
    public void setup() {
        this.mockMvc = standaloneSetup(weatherController)
                .build();
    }

    @Test
    public void getWeather() throws Exception
    {
        // Given
        double responseTemp=0.0, responseHumidity=37.0;
        double latitude=14.64072, longitude=-90.51327;
        WeatherReport weatherReport = new WeatherReport(responseTemp,responseHumidity);

        // When
        when(weatherReportService.getWeatherReport(latitude, longitude)).thenReturn(weatherReport);
        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/weather-report?latitude=14.64072&longitude=-90.51327"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        // Then
        verify(weatherReportService).getWeatherReport(latitude, longitude);
        assertThat(response.getContentAsString()).isEqualTo(new ObjectMapper().writeValueAsString(weatherReport));
    }

}
