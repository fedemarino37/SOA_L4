package com.example.TP2.usecase;

import android.content.Context;

import com.example.TP2.entity.DollarEntity;
import com.example.TP2.repository.DefaultDollarRepository;
import com.example.TP2.repository.DollarRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class GetDollarList {

    DollarRepository dollarRepository;

    public GetDollarList() {
        this.dollarRepository = new DefaultDollarRepository();
    }

    public Observable<Object> execute(Context ctx) {
        return io.reactivex.Observable.create(emitter -> {
            List<DollarEntity> dollarList = new ArrayList<>();

            dollarList.add(this.dollarRepository.retrieveMEPDollar(ctx));
            dollarList.add(this.dollarRepository.retrieveOfficialDollar(ctx));
            dollarList.add(this.dollarRepository.retrieveBlueDollar(ctx));

            emitter.onNext(dollarList);
        });
    }
}
