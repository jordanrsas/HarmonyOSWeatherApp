package com.dtse.cjra.data.services;

import com.dtse.cjra.data.model.WeatherEntity;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherServices {
    @GET("/data/2.5/weather")
    Single<Response<WeatherEntity>> getWeather(@Query("id") int id,
                                               @Query("appid") String apiKey,
                                               @Query("units") String units,
                                               @Query("lang") String language);

    @GET("/data/2.5/weather")
    Single<Response<WeatherEntity>> getWeatherByLatLng(@Query("lat") double latitude,
                                                       @Query("lon") double longitude,
                                                       @Query("appid") String apiKey,
                                                       @Query("units") String units,
                                                       @Query("lang") String language);

    @GET("/data/2.5/weather")
    Observable<Response<WeatherEntity>> getWeatherByLatLngObs(@Query("lat") double latitude,
                                                              @Query("lon") double longitude,
                                                              @Query("appid") String apiKey,
                                                              @Query("units") String units,
                                                              @Query("lang") String language);

    @GET("/data/2.5/weather")
    Call<WeatherEntity> getWeatherByLatLngCall(@Query("lat") double latitude,
                                               @Query("lon") double longitude,
                                               @Query("appid") String apiKey,
                                               @Query("units") String units,
                                               @Query("lang") String language);
}
