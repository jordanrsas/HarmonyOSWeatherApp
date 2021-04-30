package com.dtse.cjra.data.datasource;

import com.dtse.cjra.data.model.LocationEntity;
import io.reactivex.rxjava3.core.Single;

public interface LocationSource {
    Single<LocationEntity> getLocationObservable();
}
