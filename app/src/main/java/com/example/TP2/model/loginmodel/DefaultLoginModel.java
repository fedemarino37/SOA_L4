package com.example.TP2.model.loginmodel;

import android.content.Context;

import com.example.TP2.entity.LoginUserRequest;
import com.example.TP2.repository.exception.HttpBadRequestErrorException;
import com.example.TP2.repository.exception.SQLUserNotFoundException;
import com.example.TP2.usecase.LoginUser;
import com.example.TP2.usecase.RegisterUser;

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
    public void loginUser(Context ctx, LoginUserRequest loginUserRequest) {
        Observable<Object> loginUserObservable = loginUser.executeWithObservable(ctx, loginUserRequest);
        loginUserObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        presenter.onRequestStarted();
                    }

                    @Override
                    public void onNext(Object object) {
                        presenter.onLoginUserFinished();
                    }

                    @Override
                    public void onError(Throwable e) {
                        String message = e.getMessage();

                        if (e.getClass() == HttpBadRequestErrorException.class) {
                            message = "Mail o Contraseña incorrectos";
                        }

                        if(e.getClass() == SQLUserNotFoundException.class) {
                            presenter.onSQLError(loginUserRequest.getEmail());
                        }

                        presenter.onLoginError(message);
                    }

                    @Override
                    public void onComplete() {
                        presenter.onRequestFinished();
                    }
                });
    }

    @Override
    public void registerNewUser(Context ctx, String email, String name, String lastName) {
        RegisterUser regUser = new RegisterUser();
        regUser.executeCreateSQLUserEntity(ctx,name,lastName,email);
    }

//    @Override
//    public void loginNewUser(Context ctx, String email) {
//        LoginUser loginUser = new LoginUser();
//        loginUser.executeSaveUserLogin(ctx,email);
//    }
}
