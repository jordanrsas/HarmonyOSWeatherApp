package com.dtse.cjra.weatherapp.mapper;

import com.dtse.cjra.domain.component.DateComponent;
import com.dtse.cjra.domain.mapper.TransformMapper;
import com.dtse.cjra.domain.model.WeatherDto;
import com.dtse.cjra.weatherapp.manager.DateManager;
import com.dtse.cjra.weatherapp.model.Weather;

public class WeatherDetailMapper implements TransformMapper<WeatherDto, Weather> {

    private DateComponent dateManager = new DateManager();

    @Override
    public Weather mapTo(WeatherDto value) {
        return new Weather(value.getName(),
                value.getTemp(),
                value.getTempMin(),
                value.getTempMax(),
                value.getHumidity(),
                value.getWindSpeed(),
                value.getSunrise(),
                value.getSunset(),
                dateManager.getTimeFormat(value.getSunrise()),
                dateManager.getTimeFormat(value.getSunset()));
    }

    @Override
    public WeatherDto mapFrom(Weather value) {
        return null;
    }
}
