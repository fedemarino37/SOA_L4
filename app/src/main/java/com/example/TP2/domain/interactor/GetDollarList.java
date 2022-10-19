package com.example.TP2.domain.interactor;

import com.example.TP2.data.repository.DollarRepository;
import com.example.TP2.domain.Dollar;
import com.example.TP2.domain.executor.PostExecutionThread;
import com.example.TP2.domain.executor.ThreadExecutor;
import com.example.TP2.domain.mapper.DollarEntityDataMapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving a collection of all {@link Dollar}.
 */
public class GetDollarList extends UseCase<List<Dollar>, Void>{

    private final DollarRepository dollarRepository;
    private final DollarEntityDataMapper dollarEntityDataMapper;

    @Inject
    GetDollarList(DollarRepository dollarRepository, ThreadExecutor threadExecutor,
                  PostExecutionThread postExecutionThread, DollarEntityDataMapper dollarEntityDataMapper) {
        super(threadExecutor, postExecutionThread);
        this.dollarRepository = dollarRepository;
        this.dollarEntityDataMapper = dollarEntityDataMapper;
    }

    @Override
    Observable<List<Dollar>> buildUseCaseObservable(Void unused) {
        return Observable.create(emitter -> {
            try {
                List<Dollar> dollarList = new ArrayList<>();

                dollarList.add(this.dollarEntityDataMapper.transform(this.dollarRepository.retrieveOfficialDollar()));
                dollarList.add(this.dollarEntityDataMapper.transform(this.dollarRepository.retrieveBlueDollar()));
                dollarList.add(this.dollarEntityDataMapper.transform(this.dollarRepository.retrieveMEPDollar()));

                emitter.onNext(dollarList);
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
    }
}
