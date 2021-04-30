package com.dtse.cjra.weatherapp.model;

public class Weather {
    private String name;
    private double temp;
    private double tempMin;
    private double tempMax;
    private double humidity;
    private double windSpeed;
    private long sunrise;
    private long sunset;
    private String sunriseFormat;
    private String sunsetFormat;

    public Weather(String name, double temp, double tempMin, double tempMax,
                   double humidity, double windSpeed, long sunrise,
                   long sunset, String sunriseFormat, String sunsetFormat) {
        this.name = name;
        this.temp = temp;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.sunriseFormat = sunriseFormat;
        this.sunsetFormat = sunsetFormat;
    }

    public String getTemperatureText() {
        return temp + "°";
    }

    public String getMinMaxFormat() {
        return "Max: " + tempMax + "°" + " Min: " + tempMin + "°";
    }

    public String getHumidityText() {
        return humidity + " %";
    }

    public String getWindSpeedText() {
        return windSpeed + " km/h";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getTempMin() {
        return tempMin;
    }

    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }

    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public long getSunrise() {
        return sunrise;
    }

    public void setSunrise(long sunrise) {
        this.sunrise = sunrise;
    }

    public long getSunset() {
        return sunset;
    }

    public void setSunset(long sunset) {
        this.sunset = sunset;
    }

    public String getSunriseFormat() {
        return sunriseFormat;
    }

    public void setSunriseFormat(String sunriseFormat) {
        this.sunriseFormat = sunriseFormat;
    }

    public String getSunsetFormat() {
        return sunsetFormat;
    }

    public void setSunsetFormat(String sunsetFormat) {
        this.sunsetFormat = sunsetFormat;
    }
}
