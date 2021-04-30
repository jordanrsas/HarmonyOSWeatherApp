package com.dtse.cjra.domain.interactor;

import com.dtse.cjra.domain.executor.PostExecutionThread;
import com.dtse.cjra.domain.executor.ThreadExecutor;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public abstract class ObservableUseCase<Params, T> extends UseCase<Params, Observable<T>> {

    private ThreadExecutor threadExecutor;
    private PostExecutionThread postExecutionThread;

    public ObservableUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
    }

    Observable<T> observable(Params params) {
        return buildUseCase(params)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());
    }
}
