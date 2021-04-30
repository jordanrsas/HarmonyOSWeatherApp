package com.dtse.cjra.data.datasource;

import com.dtse.cjra.data.model.WeatherEntity;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Response;
import retrofit2.http.Query;


public interface WeatherSource {
    Single<Response<WeatherEntity>> getWeatherByLatLng(@Query("lat") double latitude,
                                                       @Query("lon") double longitude,
                                                       @Query("appid") String apiKey,
                                                       @Query("units") String units,
                                                       @Query("lang") String language);
}
