package com.dtse.cjra.weatherapp.registry;

import com.dtse.cjra.data.datasource.LocationSource;
import com.dtse.cjra.data.network.ApiClient;
import com.dtse.cjra.data.provider.UseCaseDataProvider;
import com.dtse.cjra.data.services.WeatherServices;
import com.dtse.cjra.domain.executor.JobExecutor;
import com.dtse.cjra.domain.executor.PostExecutionThread;
import com.dtse.cjra.domain.executor.ThreadExecutor;
import com.dtse.cjra.domain.provider.UseCaseProvider;
import com.dtse.cjra.domain.repository.LocationRepository;
import com.dtse.cjra.weatherapp.data.repository.LocationDataRepository;
import com.dtse.cjra.weatherapp.job.UIThread;
import com.dtse.cjra.weatherapp.location.LocationDataSource;
import com.dtse.cjra.weatherapp.presentation.WeatherDetailContract;
import com.dtse.cjra.weatherapp.presentation.WeatherDetailPresenter;
import ohos.app.Context;
import ohos.location.Locator;

public class WeatherRegistry implements BaseRegistry<WeatherDetailContract.View, WeatherDetailContract.Presenter> {

    private UseCaseProvider weatherDataProvider;

    public WeatherRegistry(Context context) {

        ThreadExecutor threadExecutor = new JobExecutor();
        PostExecutionThread postExecutionThread = new UIThread();

        WeatherServices apiServicesFactory = ApiClient.makeConnectionApiService(WeatherServices.class);

        Locator locator = new Locator(context);
        LocationSource locationDataSource = new LocationDataSource(locator);
        LocationRepository locationDataRepository = new LocationDataRepository(locationDataSource);

        weatherDataProvider = new UseCaseDataProvider(apiServicesFactory, locationDataRepository, threadExecutor, postExecutionThread);
    }

    @Override
    public WeatherDetailContract.Presenter provide(WeatherDetailContract.View view) {
        return new WeatherDetailPresenter(view,
                weatherDataProvider);
    }
}
