package com.example.TP2.model.userhistorymodel;

import android.content.Context;

import com.example.TP2.entity.SQLUserEntity;
import com.example.TP2.usecase.DefaultGetUsersHistory;
import com.example.TP2.usecase.GetUsersHistory;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DefaultUserHistoryModel implements UserHistoryModel {

    private final OnSendToPresenter presenter;
    private final GetUsersHistory getUsersHistory;

    public DefaultUserHistoryModel(OnSendToPresenter presenter) {
        this.presenter = presenter;
        this.getUsersHistory = new DefaultGetUsersHistory();
    }

    @Override
    public void getUserHistoryList(Context ctx) {
        Observable<Object> userHistoryObservable = getUsersHistory.executeWithObservable(ctx);
        userHistoryObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object object) {
                        List<SQLUserEntity> userHistoryList = (List<SQLUserEntity>)object;
                        presenter.setUserHistoryList(userHistoryList);
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
