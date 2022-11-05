package com.example.TP2.usecase;

import android.content.Context;

import com.example.TP2.entity.SQLUserEntity;

import java.util.List;

import io.reactivex.Observable;

public interface GetUsersHistory {
    List<SQLUserEntity> execute(Context ctx);
    Observable<Object> executeWithObservable(Context ctx);
}
