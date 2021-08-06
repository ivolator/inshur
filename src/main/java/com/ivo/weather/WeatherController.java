package com.ivo.weather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ivo.weather.dto.Day;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@RestController
public class WeatherController {

    @Autowired
    OpenWeather openWeather;

    @GetMapping("/weather/{lat}/{lng}")
    /**
     *
     */
    public ResponseEntity getWarmestForLocation(@PathVariable String lat, @PathVariable String lng) {
        try {
            Day[] days = openWeather.getWeather(Float.valueOf(lat), Float.valueOf(lng));
            return new ResponseEntity<Day>(openWeather.getWarmestForLocation(days), HttpStatus.OK);
        } catch (HttpClientErrorException e) { //return the API errors
            return ResponseEntity.status(e.getRawStatusCode()).headers(e.getResponseHeaders())
                    .body(e.getResponseBodyAsString());
        } catch (JsonProcessingException e) { //hide the JSON errors
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } //Can have an overall exception handler for the app.
    }

}
