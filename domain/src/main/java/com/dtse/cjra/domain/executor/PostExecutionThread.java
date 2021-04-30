package com.dtse.cjra.domain.executor;

import io.reactivex.rxjava3.core.Scheduler;

public interface PostExecutionThread {
    Scheduler getScheduler();
}
