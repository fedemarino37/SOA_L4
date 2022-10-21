package com.example.TP2.usecase;

import android.content.Context;

import com.example.TP2.entity.DollarEntity;
import com.example.TP2.entity.UserEntity;
import com.example.TP2.repository.sqlLite.DefaultSQLLiteRepository;
import com.example.TP2.repository.sqlLite.SQLLiteRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class GetUsersHistory {
    SQLLiteRepository sqlLiteRepo;

    public GetUsersHistory(Context context) {
        this.sqlLiteRepo = new DefaultSQLLiteRepository(context);
    }

    public Observable<Object> execute(Context ctx) {
        return io.reactivex.Observable.create(emitter -> {
            List<UserEntity> usersList = this.sqlLiteRepo.retrieveUsersHistory(ctx);
            emitter.onNext(usersList);
            // Se podria invocar el retrieve directamente en el parametro del emitter.
        });
    }

}
