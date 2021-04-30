package com.dtse.cjra.weatherapp.location;

import com.dtse.cjra.data.datasource.LocationSource;
import com.dtse.cjra.data.model.LocationEntity;
import com.dtse.cjra.weatherapp.log.Log;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.subjects.PublishSubject;
import ohos.location.Location;
import ohos.location.Locator;
import ohos.location.LocatorCallback;
import ohos.location.RequestParam;

public class LocationDataSource<T> implements LocationSource {

    private static int TIME_INTERVAL = 2;
    private static int DISTANCE_INTERVAL = 0;
    private static String TAG = "LocationHelper";

    private Locator locator;

    private RequestParam requestParam = new RequestParam(RequestParam.PRIORITY_ACCURACY, TIME_INTERVAL, DISTANCE_INTERVAL);

    public LocationDataSource(Locator locator) {
        this.locator = locator;
    }

    private final PublishSubject<LocationEntity> latestLocation = PublishSubject.create();

    @Override
    public Single<LocationEntity> getLocationObservable() {
        return Single.create(emitter -> {
            locator.requestOnce(requestParam, new LocatorCallback() {
                @Override
                public void onLocationReport(Location location) {
                    emitter.onSuccess(new LocationEntity(location.getLatitude(), location.getLongitude()));
                }

                @Override
                public void onStatusChanged(int i) {
                    Log.i(TAG, "onStatusChanged: " + i);
                }

                @Override
                public void onErrorReport(int i) {
                    emitter.onError(new Throwable("Error get location error: " + i));
                }
            });
        });
    }
}
