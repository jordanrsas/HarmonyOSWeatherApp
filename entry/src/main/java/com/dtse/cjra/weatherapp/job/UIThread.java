package com.dtse.cjra.weatherapp.job;

import com.dtse.cjra.domain.executor.PostExecutionThread;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.openharmony.schedulers.OpenHarmonySchedulers;

public class UIThread implements PostExecutionThread {
    @Override
    public Scheduler getScheduler() {
        return OpenHarmonySchedulers.mainThread();
    }
}
