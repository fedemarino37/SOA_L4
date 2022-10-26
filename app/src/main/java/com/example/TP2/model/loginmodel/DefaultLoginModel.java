package com.example.TP2.model.loginmodel;

import android.content.Context;
import android.util.Log;

import com.example.TP2.entity.LoginUserRequest;
import com.example.TP2.usecase.LoginUser;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DefaultLoginModel implements LoginModel {

    LoginUser loginUser;
    private final OnSendToPresenter presenter;


    public DefaultLoginModel(OnSendToPresenter presenter) {
        this.presenter = presenter;
        loginUser = new LoginUser();
    }

    @Override
    public void sendMessage(Context ctx) {
        //presenter.onFinished("MENSAJE AL PRESENTADOR");
    }

    @Override
    public void loginUser(Context ctx, LoginUserRequest loginUserRequest) {
        Observable<Object> loginUserObservable = loginUser.executeWithObservable(ctx, loginUserRequest);
        loginUserObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("Test", "Test msg");
                    }

                    @Override
                    public void onNext(Object object) {
                        presenter.onLoginUserFinished();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("Test", e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        Log.d("Test", "log after observable");
    }

}
