package com.example.TP2.usecase;

import android.content.Context;

import com.example.TP2.entity.LoginUserRequest;
import com.example.TP2.repository.exception.SQLUserNotFoundException;

import io.reactivex.Observable;

public interface SaveUserLogin {
    void execute(Context ctx, String email) throws SQLUserNotFoundException;
    Observable<Void> executeWithObservable(Context ctx, LoginUserRequest loginUserRequest);
}
