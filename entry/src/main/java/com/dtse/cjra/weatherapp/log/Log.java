package com.dtse.cjra.weatherapp.log;

import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

public class Log {
    private static final String APP_TAG = "weatherapp";
    private static final int DOMAIN =  0x00201;
    private static final String FORMAT = "%{public}s: %{public}s";
    private static final HiLogLabel LABLE = new HiLogLabel(HiLog.LOG_APP, DOMAIN, APP_TAG);

    public static void e(String tag, String message) {
        HiLog.error(LABLE, FORMAT, tag, message);
    }

    public static void i(String tag, String message) {
        HiLog.info(LABLE, FORMAT, tag, message);
    }

    public static void d(String tag, String message) {
        HiLog.debug(LABLE, FORMAT, tag, message);
    }
}
