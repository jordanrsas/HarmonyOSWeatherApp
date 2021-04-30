package com.dtse.cjra.weatherapp.data.repository;

import com.dtse.cjra.data.datasource.LocationSource;
import com.dtse.cjra.data.mapper.LocationDataMapper;
import com.dtse.cjra.data.model.LocationEntity;
import com.dtse.cjra.domain.mapper.TransformMapper;
import com.dtse.cjra.domain.model.LocationDto;
import com.dtse.cjra.domain.repository.LocationRepository;
import io.reactivex.rxjava3.core.Single;

public class LocationDataRepository implements LocationRepository {

    private LocationSource LocationSource;
    private TransformMapper<LocationEntity, LocationDto> mapper = new LocationDataMapper();

    public LocationDataRepository(LocationSource locationDataSource) {
        this.LocationSource = locationDataSource;
    }

    @Override
    public Single<LocationDto> getUserLocation() {
        return LocationSource.getLocationObservable().map(locationDataSource -> mapper.mapTo(locationDataSource));
    }
}
