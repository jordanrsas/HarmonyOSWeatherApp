package com.dtse.cjra.weatherapp.manager;

import com.dtse.cjra.domain.component.DateComponent;
import ohos.global.icu.text.SimpleDateFormat;
import ohos.global.icu.util.TimeZone;

import java.util.Date;

public class DateManager implements DateComponent {

    private static String DATE_PATTERN = "hh:mm:ss a zzz";

    @Override
    public String getTimeFormat(long timeStamp) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_PATTERN);
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        return simpleDateFormat.format(new Date(timeStamp));
    }
}
