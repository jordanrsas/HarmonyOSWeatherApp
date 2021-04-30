package com.dtse.cjra.domain.provider;

import com.dtse.cjra.domain.interactor.SingleUseCase;
import com.dtse.cjra.domain.model.LocationDto;
import com.dtse.cjra.domain.model.RequestWeatherDto;
import com.dtse.cjra.domain.model.WeatherDto;

public interface UseCaseProvider {
    SingleUseCase<RequestWeatherDto, WeatherDto> getWeatherByLatLngUseCase();

    SingleUseCase<Void, LocationDto> getUserLocationUseCase();
}
