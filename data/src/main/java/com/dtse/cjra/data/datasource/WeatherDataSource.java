package com.dtse.cjra.data.datasource;

import com.dtse.cjra.data.model.WeatherEntity;
import com.dtse.cjra.data.services.WeatherServices;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Response;


public class WeatherDataSource implements WeatherSource {

    WeatherServices services;

    public WeatherDataSource(WeatherServices services) {
        this.services = services;
    }

    @Override
    public Single<Response<WeatherEntity>> getWeatherByLatLng(double latitude, double longitude, String apiKey, String units, String language) {
        return services.getWeatherByLatLng(latitude, longitude, apiKey, units, language);
    }
}
