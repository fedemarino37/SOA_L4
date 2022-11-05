package com.example.TP2.model.registermodel;

import android.content.Context;

import com.example.TP2.entity.RegisterUserRequest;
import com.example.TP2.usecase.DefaultRegisterUser;
import com.example.TP2.usecase.RegisterUser;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DefaultRegisterModel implements RegisterModel {

    private final RegisterUser registerUser;
    private final OnSendToPresenter presenter;

    public DefaultRegisterModel(OnSendToPresenter presenter) {
        this.presenter = presenter;
        this.registerUser = new DefaultRegisterUser();
    }

    @Override
    public void registerUser(Context ctx, RegisterUserRequest registerUserRequest) {
        Observable<Object> registerUserObservable = registerUser.executeWithObservable(ctx, registerUserRequest);
        registerUserObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object object) {
                        presenter.onRegisterUserFinished();
                    }

                    @Override
                    public void onError(Throwable e) {
                        String message = e.getMessage();
                        presenter.onRegisterError(message);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
