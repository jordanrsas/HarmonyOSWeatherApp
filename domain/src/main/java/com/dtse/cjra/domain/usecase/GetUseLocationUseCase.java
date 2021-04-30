package com.dtse.cjra.domain.usecase;

import com.dtse.cjra.domain.executor.PostExecutionThread;
import com.dtse.cjra.domain.executor.ThreadExecutor;
import com.dtse.cjra.domain.interactor.SingleUseCase;
import com.dtse.cjra.domain.model.LocationDto;
import com.dtse.cjra.domain.repository.LocationRepository;
import io.reactivex.rxjava3.core.Single;

public class GetUseLocationUseCase extends SingleUseCase<Void, LocationDto> {

    LocationRepository repository;

    public GetUseLocationUseCase(LocationRepository repository,
                                 ThreadExecutor threadExecutor,
                                 PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Single<LocationDto> buildUseCase(Void o) {
        return repository
                .getUserLocation()
                .onErrorResumeNext(error -> Single.error(new Throwable("Error on USECASE: GetUseLocationUseCase")));
    }
}
