package com.dtse.cjra.domain.repository;

import com.dtse.cjra.domain.model.WeatherDto;
import io.reactivex.rxjava3.core.Single;

public interface WeatherRepository {
    Single<WeatherDto> getWeatherLatLng(double latitude, double longitude);
}
