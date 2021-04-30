package com.dtse.cjra.domain.repository;

import com.dtse.cjra.domain.model.LocationDto;
import io.reactivex.rxjava3.core.Single;

public interface LocationRepository {
    Single<LocationDto> getUserLocation();
}
