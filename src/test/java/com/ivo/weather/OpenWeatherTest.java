package com.ivo.weather;

import com.ivo.weather.dto.Day;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OpenWeatherTest {

    @Test
    void getWarmestForLocationTempOnly() {
        Day day1 = new Day(51f,20f,10001);
        Day day2 = new Day(52f,10f,10002);
        //should select this
        Day day3 = new Day(53f,25f,10003);

        Day[] days = {day1,day2,day3};

        OpenWeather openWeather = new OpenWeather();
        Day actual = openWeather.getWarmestForLocation(days);

        Assertions.assertEquals(day3, actual);
    }

    @Test
    void getWarmestForLocationTempLowestHumidity() {

        //should select first because of low humidity
        Day day1 = new Day(51f,25f,10001);
        Day day2 = new Day(52f,10f,10002);
        Day day3 = new Day(53f,25f,10003);

        Day[] days = {day1,day2,day3};

        OpenWeather openWeather = new OpenWeather();
        Day actual = openWeather.getWarmestForLocation(days);

        Assertions.assertEquals(day1, actual);
    }

    @Test
    void getWarmestForLocationTempSameHighTandLowH() {
        //should select first because earliest day
        Day day1 = new Day(53f,25f,10001);
        Day day2 = new Day(52f,10f,10002);
        Day day3 = new Day(53f,25f,10003);

        Day[] days = {day1,day2,day3};

        OpenWeather openWeather = new OpenWeather();
        Day actual = openWeather.getWarmestForLocation(days);

        Assertions.assertEquals(day1, actual);
    }
}