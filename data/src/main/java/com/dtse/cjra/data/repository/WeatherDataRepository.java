package com.dtse.cjra.data.repository;

import com.dtse.cjra.data.datasource.WeatherSource;
import com.dtse.cjra.data.mapper.WeatherDataMapper;
import com.dtse.cjra.domain.model.WeatherDto;
import com.dtse.cjra.domain.repository.WeatherRepository;
import io.reactivex.rxjava3.core.Single;

public class WeatherDataRepository implements WeatherRepository {

    private WeatherSource dataSource;
    private WeatherDataMapper mapper = new WeatherDataMapper();
    private static String API_KEY = "openweathermap_api_key";

    public WeatherDataRepository(WeatherSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Single<WeatherDto> getWeatherLatLng(double latitude, double longitude) {
        return dataSource.getWeatherByLatLng(latitude, longitude, API_KEY, "metric", "es")
                .onErrorResumeNext(throwable -> Single.error(new Exception("Error: onErrorResumeNext")))
                .map(weatherEntityResponse -> {
                    if (weatherEntityResponse.isSuccessful()) {
                        return mapper.mapTo(weatherEntityResponse.body());
                    } else {
                        throw new Exception("Error: weatherEntityResponse");
                    }
                });
    }
}
