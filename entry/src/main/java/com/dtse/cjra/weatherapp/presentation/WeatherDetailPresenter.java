package com.dtse.cjra.weatherapp.presentation;

import com.dtse.cjra.domain.mapper.TransformMapper;
import com.dtse.cjra.domain.model.RequestWeatherDto;
import com.dtse.cjra.domain.model.WeatherDto;
import com.dtse.cjra.domain.provider.UseCaseProvider;
import com.dtse.cjra.weatherapp.log.Log;
import com.dtse.cjra.weatherapp.mapper.WeatherDetailMapper;
import com.dtse.cjra.weatherapp.model.Weather;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class WeatherDetailPresenter implements WeatherDetailContract.Presenter {

    private static final String TAG = "Presenter";
    private WeatherDetailContract.View weatherView;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private TransformMapper<WeatherDto, Weather> weatherMapper = new WeatherDetailMapper();
    private UseCaseProvider dataProvider;

    public WeatherDetailPresenter(WeatherDetailContract.View view,
                                  UseCaseProvider weatherUseCaseProvider) {
        this.weatherView = view;
        this.dataProvider = weatherUseCaseProvider;
    }

    @Override
    public void init() {
        weatherView.checkLocationPermissions();
    }

    @Override
    public void getWeatherByLatLng() {
        Log.i(TAG, "getWeatherByLatLng");
        compositeDisposable.add(
                dataProvider.getUserLocationUseCase()
                        .execute(null)
                        .doOnSubscribe(consumer -> {
                            Log.d(TAG, "doOnSubscribe getUserLocationUseCase");
                        })
                        .flatMap(locationDto -> {
                            return dataProvider.getWeatherByLatLngUseCase()
                                    .execute(new RequestWeatherDto(locationDto.getLatitude(), locationDto.getLongitude()))
                                    .doOnSubscribe(consumer -> {
                                        Log.d(TAG, "doOnSubscribe getWeatherByLatLngUseCase");
                                    });
                        })
                        .subscribe(response -> {
                            Log.d(TAG, "Response: " + response.toString());
                            weatherView.printWeatherDetail(weatherMapper.mapTo(response));
                        }, error -> {
                            weatherView.printError("Error get weather by LatLng: " + error.getMessage());
                        })
        );
    }

    @Override
    public void initDateTime() {
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG, Locale.getDefault());
        weatherView.printDate(dateFormat.format(new Date()));
    }

    @Override
    public void stop() {
        if (compositeDisposable.isDisposed()) {
            compositeDisposable.clear();
        }
    }
}
