package com.dtse.cjra.domain.interactor;

import io.reactivex.rxjava3.disposables.Disposable;

abstract class UseCase<Params, R> {
    private Disposable disposable = null;

    protected abstract R buildUseCase(Params params);

    public void dispose() {
        if (disposable != null) {
            disposable.dispose();
        }
    }

    protected void subscribe(Disposable subscription) {
        disposable = subscription;
    }
}
