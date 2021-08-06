Connect to the OpenWeather API found here (https://openweathermap.org/api/one-call-api).
For the given lat / long, parse the API data and find the warmest day over the next 7 days. If multiple days have the same highest temperature, then go for the day that has the warmest temperature and the lowest humidity.
If multiple days have both the warmest temperature and lowest humidity, then display the first of those days.
The output can be textual or graphical, html or json.

Solution:
http://localhost:8080/weather/{lat}/{long}

http://localhost:8080/weather/50.824955973889/-0.13878781625840952a
returns a simple JSON object with humidity, temp and timestamp:
  {
    "humidity": 59.0,
    "max": 19.48,
    "dt": 1628769600
  }
