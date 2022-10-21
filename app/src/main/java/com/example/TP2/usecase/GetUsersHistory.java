package com.example.TP2.usecase;

import android.content.Context;

import com.example.TP2.repository.sqlite.DefaultSQLUserRepository;
import com.example.TP2.repository.sqlite.SQLUserRepository;

import io.reactivex.Observable;

public class GetUsersHistory {
    SQLUserRepository sqlLiteRepo;

    public GetUsersHistory() {
        this.sqlLiteRepo = new DefaultSQLUserRepository();
    }

    public Observable<Object> execute(Context ctx) {
        return io.reactivex.Observable.create(emitter -> {
            emitter.onNext(this.sqlLiteRepo.retrieveUsersHistory(ctx));
        });
    }

    public Observable<Object> executeWithObservable(Context ctx) {
        return io.reactivex.Observable.create(emitter -> {
            emitter.onNext(execute(ctx));
        });
    }

}
