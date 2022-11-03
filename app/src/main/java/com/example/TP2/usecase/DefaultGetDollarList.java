package com.example.TP2.usecase;

import android.content.Context;

import com.example.TP2.entity.DollarEntity;
import com.example.TP2.repository.exception.NetworkConnectionException;
import com.example.TP2.repository.rest.DefaultDollarRepository;
import com.example.TP2.repository.rest.DollarRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class DefaultGetDollarList implements GetDollarList {

    private final DollarRepository dollarRepository;
    private final RegisterEvent registerEvent;

    public DefaultGetDollarList() {
        this.dollarRepository = new DefaultDollarRepository();
        this.registerEvent = new DefaultRegisterEvent();
    }

    @Override
    public List<DollarEntity> execute(Context ctx) throws NetworkConnectionException, IOException {
        List<DollarEntity> dollarList = new ArrayList<>();

        dollarList.add(this.dollarRepository.retrieveOfficialDollar(ctx));
        dollarList.add(this.dollarRepository.retrieveBlueDollar(ctx));
        dollarList.add(this.dollarRepository.retrieveMEPDollar(ctx));
        dollarList.add(this.dollarRepository.retrieveTouristDollar(ctx));

        registerEvent.execute(ctx, "dollar-event", "dollar list updated");

        return dollarList;
    }

    @Override
    public Observable<Object> executeWithObservable(Context ctx) {
        return io.reactivex.Observable.create(emitter -> {
            emitter.onNext(execute(ctx));
        });
    }
}
