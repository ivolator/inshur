package com.ivo.weather;

import com.ivo.weather.dto.Day;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OpenWeatherTest {

    @Test
    /**
     * finds warmest day
     */
    void getWarmestForLocationTempOnly() {
        Day day1 = new Day(51f, 20f, 10001);
        Day day2 = new Day(52f, 10f, 10002);
        //should select this
        Day day3 = new Day(53f, 25f, 10003);

        Day[] days = {day1, day2, day3};

        OpenWeather openWeather = new OpenWeather();
        Day actual = openWeather.getWarmestForLocation(days);

        Assertions.assertEquals(day3, actual);
    }

    @Test
    /**
     * warmest day lowest humidity
     */
    void getWarmestForLocationTempLowestHumidity() {

        Day day0 = new Day(60f, 10f, 10002);
        //should select 1 because of lower humidity
        Day day1 = new Day(51f, 25f, 10001);
        Day day2 = new Day(52f, 10f, 10002);
        Day day3 = new Day(53f, 25f, 10003);

        Day[] days = {day0, day1, day2, day3};

        OpenWeather openWeather = new OpenWeather();
        Day actual = openWeather.getWarmestForLocation(days);

        Assertions.assertEquals(day1, actual);
    }

    @Test
    /**
     * the forst of the warmest days with same humidity
     */
    void getWarmestForLocationTempSameHighTandLowH() {
        //should select first because earliest day
        Day day1 = new Day(53f, 25f, 10001);
        Day day2 = new Day(52f, 10f, 10002);
        Day day3 = new Day(53f, 25f, 10003);
        Day day4 = new Day(53f, 25f, 10004);

        Day[] days = {day2, day1, day3, day4};

        OpenWeather openWeather = new OpenWeather();
        Day actual = openWeather.getWarmestForLocation(days);

        Assertions.assertEquals(day1, actual);
    }
}