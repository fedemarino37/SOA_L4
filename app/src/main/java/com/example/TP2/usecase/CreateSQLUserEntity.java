package com.example.TP2.usecase;

import android.content.Context;

import io.reactivex.Observable;

public interface CreateSQLUserEntity {
    void execute(Context ctx, String name, String lastName, String email);
    Observable<Void> executeWithObservable(Context ctx, String name, String lastName, String email);
}
