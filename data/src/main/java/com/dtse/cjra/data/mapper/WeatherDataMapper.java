package com.dtse.cjra.data.mapper;

import com.dtse.cjra.data.model.WeatherEntity;
import com.dtse.cjra.domain.mapper.TransformMapper;
import com.dtse.cjra.domain.model.WeatherDto;

public class WeatherDataMapper implements TransformMapper<WeatherEntity, WeatherDto> {

    @Override
    public WeatherDto mapTo(WeatherEntity value) {
        WeatherDto dto = new WeatherDto();
        WeatherEntity.MainData mainData = value.getMainData();
        WeatherEntity.Sys sys = value.getSys();

        dto.setName(value.getName());
        dto.setHumidity(mainData.getHumidity());
        dto.setSunrise(sys.getSunrise() * 1000);
        dto.setSunset(sys.getSunset() * 1000);
        dto.setTemp(mainData.getTemp());
        dto.setTempMax(mainData.getTempMax());
        dto.setTempMin(mainData.getTempMin());
        dto.setWindSpeed(value.getWind().getSpeed());
        return dto;
    }

    @Override
    public WeatherEntity mapFrom(WeatherDto value) {
        return null;
    }
}
