package com.dtse.cjra.domain.interactor;

import com.dtse.cjra.domain.executor.PostExecutionThread;
import com.dtse.cjra.domain.executor.ThreadExecutor;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public abstract class SingleUseCase<Params, T> extends UseCase<Params, Single<T>> {

    ThreadExecutor threadExecutor;
    PostExecutionThread postExecutionThread;

    public SingleUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
    }

    public Single<T> execute(Params params) {
        return buildUseCase(params)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());
    }

    public void execute(Params params, DisposableSingleObserver<T> observer) {
        @NonNull Single<T> observable = buildUseCase(params)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());
        subscribe(observable.subscribeWith(observer));
    }
}
