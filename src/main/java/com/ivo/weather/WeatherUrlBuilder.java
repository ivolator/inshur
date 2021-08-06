package com.ivo.weather;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WeatherUrlBuilder {

    @Value("${weather.url}")
    protected String url;

    @Value("${weather.apiKey}")
    protected String apiKey;


    /**
     * Simple URL composition
     *
     * @param lat
     * @param lng
     * @return
     */
    public String getUrl(Float lat, Float lng) {
        return url + "?" + "lat=" + lat + "&lon=" + lng + "&units=metric&exclude=hourly,minutely,alerts,current&appid=" + apiKey;
    }


}
