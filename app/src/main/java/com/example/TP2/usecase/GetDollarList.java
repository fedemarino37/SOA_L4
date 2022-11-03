package com.example.TP2.usecase;

import android.content.Context;

import com.example.TP2.entity.DollarEntity;
import com.example.TP2.repository.exception.NetworkConnectionException;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observable;

public interface GetDollarList {
    List<DollarEntity> execute(Context ctx) throws NetworkConnectionException, IOException;
    Observable<Object> executeWithObservable(Context ctx);
}
