package com.example.TP2.model.dollarmodel;

import android.content.Context;

import com.example.TP2.entity.DollarEntity;
import com.example.TP2.usecase.GetDollarList;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DefaultDollarModel implements DollarModel {

    private GetDollarList getDollarList;
    private final OnSendToPresenter presenter;

    public DefaultDollarModel(OnSendToPresenter presenter) {
        this.getDollarList = new GetDollarList();
        this.presenter = presenter;
    }

    @Override
    public void getDollarList(Context ctx) {
        Observable<Object> dollarEntity = getDollarList.executeWithObservable(ctx);
        dollarEntity.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object object) {
                        List<DollarEntity> dollarList = (List<DollarEntity>)object;
                        presenter.setDollarList(dollarList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        presenter.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {}
                });
    }
}
