package com.ivo.weather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ivo.weather.dto.Day;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Optional;


@Service
/**
 * Contains the API call and Filtering
 */
public class OpenWeather {

    @Autowired
    protected WeatherUrlBuilder weatherUrlBuilder;

    @Autowired
    protected RestTemplate restTpl;

    /**
     * Fetch weather data
     *
     * @param lat
     * @param lng
     * @return Day[]
     * @throws JsonProcessingException
     */
    protected Day[] getWeather(Float lat, Float lng) throws JsonProcessingException {
        String response = restTpl.getForObject(weatherUrlBuilder.getUrl(lat, lng), String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonNode jsonNodeRoot = objectMapper.readTree(response);
        JsonNode daily = jsonNodeRoot.get("daily");
        Day[] days = objectMapper.readValue(daily.toString(), Day[].class);
        return days;
    }

    /**
     * Filter out the warmest day
     *
     * @param days
     * @return Day
     */
    public Day getWarmestForLocation(Day[] days) {
        Optional<Day> day = Arrays.stream(days).max((Day d1, Day d2) -> {
            if (d1.getMax() - d2.getMax() == 0) {//get the lowest humidity
                int humidity = (int) (d2.getHumidity() - d1.getHumidity());
                if (humidity == 0) {// get the first of the days
                    return d2.getDt() - d1.getDt();
                }
                return humidity;
            }
            return (int) (d1.getMax() - d2.getMax());//get the highest temp
        });
        return day.orElse(new Day());
    }


    public WeatherUrlBuilder getWeatherUrlBuilder() {
        return weatherUrlBuilder;
    }
}
