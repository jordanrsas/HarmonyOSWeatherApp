package com.dtse.cjra.data.provider;

import com.dtse.cjra.data.datasource.WeatherDataSource;
import com.dtse.cjra.data.datasource.WeatherSource;
import com.dtse.cjra.data.repository.WeatherDataRepository;
import com.dtse.cjra.data.services.WeatherServices;
import com.dtse.cjra.domain.executor.PostExecutionThread;
import com.dtse.cjra.domain.executor.ThreadExecutor;
import com.dtse.cjra.domain.interactor.SingleUseCase;
import com.dtse.cjra.domain.model.LocationDto;
import com.dtse.cjra.domain.model.RequestWeatherDto;
import com.dtse.cjra.domain.model.WeatherDto;
import com.dtse.cjra.domain.provider.UseCaseProvider;
import com.dtse.cjra.domain.repository.LocationRepository;
import com.dtse.cjra.domain.repository.WeatherRepository;
import com.dtse.cjra.domain.usecase.GetUseLocationUseCase;
import com.dtse.cjra.domain.usecase.GetWeatherLatLngUseCase;

public class UseCaseDataProvider implements UseCaseProvider {

    private ThreadExecutor threadExecutor;
    private PostExecutionThread postExecutionThread;

    private WeatherRepository weatherApiRepository;
    LocationRepository locationRepository;

    public UseCaseDataProvider(WeatherServices apiServices,
                               LocationRepository locationRepository,
                               ThreadExecutor threadExecutor,
                               PostExecutionThread postExecutionThread) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;

        WeatherSource dataApiSource = new WeatherDataSource(apiServices);
        weatherApiRepository = new WeatherDataRepository(dataApiSource);

        this.locationRepository = locationRepository;
    }

    @Override
    public SingleUseCase<RequestWeatherDto, WeatherDto> getWeatherByLatLngUseCase() {
        return new GetWeatherLatLngUseCase(weatherApiRepository, threadExecutor, postExecutionThread);
    }

    @Override
    public SingleUseCase<Void, LocationDto> getUserLocationUseCase() {
        return new GetUseLocationUseCase(locationRepository, threadExecutor, postExecutionThread);
    }
}
