package com.ivo.weather.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.boot.jackson.JsonComponent;

import java.util.Map;

@JsonComponent
/**
 * Simplified representation of the OpenWeather API response
 */
public class Day {
    protected Float humidity;
    protected Float max;
    protected Integer dt;

    public Day() {
    }

    public Day(Float humidity, Float max, Integer dt) {
        this.humidity = humidity;
        this.max = max;
        this.dt = dt;
    }

    @JsonProperty("temp")
    public void getMaxTemp(Map<String, String> temps) {
        this.max = Float.valueOf(temps.get("max"));
    }

    public Float getHumidity() {
        return humidity;
    }

    public Float getMax() {
        return max;
    }

    public Integer getDt() {
        return dt;
    }
}

