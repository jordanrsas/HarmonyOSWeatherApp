package com.dtse.cjra.weatherapp;

import com.dtse.cjra.weatherapp.log.Log;
import com.dtse.cjra.weatherapp.model.Weather;
import com.dtse.cjra.weatherapp.presentation.WeatherDetailContract;
import com.dtse.cjra.weatherapp.registry.WeatherRegistry;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Text;
import ohos.agp.window.dialog.ToastDialog;
import ohos.bundle.IBundleManager;

public class MainAbility extends Ability implements WeatherDetailContract.View {

    private static String TAG = "MainAbility";
    private static int REQUEST_CODE = 2055;
    private Text temperature;
    private Text date;
    private Text city;
    private Text minMaxTemp;
    private Text humidity;
    private Text wind;
    private Text sunrise;
    private Text sunset;

    private WeatherDetailContract.Presenter presenter;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        setUIContent(ResourceTable.Layout_ability_main);
        initViews();
        Log.i(TAG, "onStart");
        presenter = new WeatherRegistry(getContext()).provide(this);
        presenter.init();
        presenter.initDateTime();
    }

    @Override
    public void printWeatherDetail(Weather weather) {
        temperature.setText(weather.getTemperatureText());
        city.setText(weather.getName());
        minMaxTemp.setText(weather.getMinMaxFormat());
        humidity.setText(weather.getHumidityText());
        wind.setText(weather.getWindSpeedText());
        sunrise.setText(weather.getSunriseFormat());
        sunset.setText(weather.getSunsetFormat());
    }

    @Override
    public void printError(String errorMessage) {
        Log.e(TAG, "¨Print error: " + errorMessage);
        new ToastDialog(this)
                .setText(errorMessage)
                .show();
    }

    @Override
    protected void onActive() {
        super.onActive();
        Log.i(TAG, "onActive");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
        presenter.stop();
    }

    private void initViews() {
        temperature = (Text) findComponentById(ResourceTable.Id_text_temperature);
        date = (Text) findComponentById(ResourceTable.Id_text_date);
        city = (Text) findComponentById(ResourceTable.Id_text_city);
        minMaxTemp = (Text) findComponentById(ResourceTable.Id_min_max_temperature);
        humidity = (Text) findComponentById(ResourceTable.Id_humidity);
        wind = (Text) findComponentById(ResourceTable.Id_wind_speed);
        sunrise = (Text) findComponentById(ResourceTable.Id_sunrise);
        sunset = (Text) findComponentById(ResourceTable.Id_sunset);
    }

    @Override
    public void printDate(String formatDate) {
        date.setText(formatDate);
    }

    @Override
    public void checkLocationPermissions() {
        Log.i(TAG, "checkLocationPermissions");
        if (verifySelfPermission("ohos.permission.LOCATION") != IBundleManager.PERMISSION_GRANTED) {
            // The application has not been granted the permission
            if (canRequestPermission("ohos.permission.LOCATION")) {
                // Check whether permission authorization can be implemented via a dialog box
                // (at initial request or when the user has not chosen the option of "don't ask again" after rejecting a previous request).
                Log.i(TAG, "PERMISSON NOT GRANTED, canRequestPermission");
                requestPermissionsFromUser(new String[]{"ohos.permission.LOCATION"}, REQUEST_CODE);
            } else {
                // Display the reason why the application requests the permission and prompt the user to grant the permission.
                Log.i(TAG, "PERMISSON NOT GRANTED, can not RequestPermission");
            }
        } else {
            // The permission has been granted.
            Log.i(TAG, "getWeatherByLatLng");
            presenter.getWeatherByLatLng();
        }
    }

    @Override
    public void onRequestPermissionsFromUserResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsFromUserResult(requestCode, permissions, grantResults);
        Log.i(TAG, "onRequestPermissionsFromUserResult");
        switch (requestCode) {
            case 2055: {
                Log.i(TAG, "onRequestPermissionsFromUserResult");
                if (grantResults.length > 0
                        && grantResults[0] == IBundleManager.PERMISSION_GRANTED) {
                    // The permission is granted.
                    //Note: During permission check, an interface may be considered to have no required permissions
                    // due to time difference. Therefore, it is necessary to capture and process the exception thrown
                    // by such an interface.
                    Log.i(TAG, "presenter.getWeatherByLatLng()");
                    presenter.getWeatherByLatLng();
                } else {
                    // The permission request is rejected.
                    Log.i(TAG, "The permission request is rejected.");
                }
                return;
            }
            default:
                Log.i(TAG, "IllegalStateException");
                throw new IllegalStateException("Unexpected value: " + requestCode);
        }
    }
}
