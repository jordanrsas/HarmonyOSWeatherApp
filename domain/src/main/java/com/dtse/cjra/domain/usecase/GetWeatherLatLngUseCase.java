package com.dtse.cjra.domain.usecase;

import com.dtse.cjra.domain.executor.PostExecutionThread;
import com.dtse.cjra.domain.executor.ThreadExecutor;
import com.dtse.cjra.domain.interactor.SingleUseCase;
import com.dtse.cjra.domain.model.RequestWeatherDto;
import com.dtse.cjra.domain.model.WeatherDto;
import com.dtse.cjra.domain.repository.WeatherRepository;
import io.reactivex.rxjava3.core.Single;

public class GetWeatherLatLngUseCase extends SingleUseCase<RequestWeatherDto, WeatherDto> {

    WeatherRepository repository;

    public GetWeatherLatLngUseCase(WeatherRepository weatherRepository,
                                   ThreadExecutor threadExecutor,
                                   PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        repository = weatherRepository;
    }

    @Override
    protected Single<WeatherDto> buildUseCase(RequestWeatherDto requestWeatherDto) {
        return repository
                .getWeatherLatLng(requestWeatherDto.getLatitude(), requestWeatherDto.getLongitude())
                .onErrorResumeNext(error -> Single.error(new Throwable("Error on USECASE: GetWeatherLatLng")));
    }
}
