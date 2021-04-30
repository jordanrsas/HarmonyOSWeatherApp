package com.dtse.cjra.data.mapper;

import com.dtse.cjra.data.model.LocationEntity;
import com.dtse.cjra.domain.mapper.TransformMapper;
import com.dtse.cjra.domain.model.LocationDto;

public class LocationDataMapper implements TransformMapper<LocationEntity, LocationDto> {
    @Override
    public LocationDto mapTo(LocationEntity value) {
        return new LocationDto(value.getLatitude(), value.getLongitude());
    }

    @Override
    public LocationEntity mapFrom(LocationDto value) {
        return null;
    }
}
