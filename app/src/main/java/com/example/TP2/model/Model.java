package com.example.TP2.model;

import android.content.Context;
import android.util.Log;

import com.example.TP2.repository.rest.DefaultDollarRepository;
import com.example.TP2.repository.rest.DollarRepository;
import com.example.TP2.usecase.GetDollarList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class Model implements ModelMVP {

    DollarRepository dollarRepository;
    GetDollarList getDollarList;

    public Model() {
        dollarRepository = new DefaultDollarRepository();
        getDollarList = new GetDollarList();
    }

    @Override
    public void sendMessage(Context ctx, OnSendToPresenter presenter) {
            Observable<Object> dollarEntity = getDollarList.execute(ctx);
            dollarEntity.subscribe(new Observer<Object>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(Object object) {
                    //Float buyValue = dollarEntity.getBuyValue();
                    presenter.onFinished("Dollar compra: ");
                }

                @Override
                public void onError(Throwable e) {
                    Log.d("Test", e.getMessage());
                }

                @Override
                public void onComplete() {

                }
            });

        //presenter.onFinished("MENSAJE AL PRESENTADOR");
    }



}
