package com.example.TP2.model.loginmodel;

import android.content.Context;
import android.util.Log;

import com.example.TP2.entity.LoginUserRequest;
import com.example.TP2.entity.LoginUserResponse;
import com.example.TP2.model.mainmodel.MainModel;
import com.example.TP2.usecase.LoginUser;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class DefaultLoginModel implements MainModel {

    LoginUser loginUser;

    public DefaultLoginModel() {
        loginUser = new LoginUser();
    }

    @Override
    public void sendMessage(Context ctx, OnSendToPresenter presenter) {
            LoginUserRequest loginUserRequest = new LoginUserRequest("aa@hotmai.com", "aaaa");
            Observable<Object> loginUserResponseEntity = loginUser.executeWithObservable(ctx, loginUserRequest);
                loginUserResponseEntity.subscribe(new Observer<Object>() {
                @Override
                public void onSubscribe(Disposable d) {
                    Log.d("Test", "Test msg");
                }

                @Override
                public void onNext(Object object) {
                    presenter.onFinished("Hola");
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
