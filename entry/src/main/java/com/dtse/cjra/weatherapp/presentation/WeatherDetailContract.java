package com.dtse.cjra.weatherapp.presentation;

import com.dtse.cjra.weatherapp.model.Weather;

public interface WeatherDetailContract {

    interface View {
        void checkLocationPermissions();

        void printWeatherDetail(Weather weather);

        void printDate(String formatDate);

        void printError(String errorMessage);
    }

    interface Presenter {
        void init();

        void getWeatherByLatLng();

        void initDateTime();

        void stop();
    }
}
