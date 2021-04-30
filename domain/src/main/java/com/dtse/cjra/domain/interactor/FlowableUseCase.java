package com.dtse.cjra.domain.interactor;

import com.dtse.cjra.domain.executor.PostExecutionThread;
import com.dtse.cjra.domain.executor.ThreadExecutor;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public abstract class FlowableUseCase<Params, T> extends UseCase<Void, Flowable<T>> {

    ThreadExecutor threadExecutor;
    PostExecutionThread postExecutionThread;

    public FlowableUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
    }

    public void execute() {
        Flowable<T> observable = buildUseCase(null)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());
    }
}
