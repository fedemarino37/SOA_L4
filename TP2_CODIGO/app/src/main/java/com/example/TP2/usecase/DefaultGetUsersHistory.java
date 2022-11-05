package com.example.TP2.usecase;

import android.content.Context;

import com.example.TP2.entity.SQLUserEntity;
import com.example.TP2.repository.sqlite.DefaultSQLUserRepository;
import com.example.TP2.repository.sqlite.SQLUserRepository;

import java.util.List;

import io.reactivex.Observable;

public class DefaultGetUsersHistory implements GetUsersHistory {
    private final SQLUserRepository sqlLiteRepository;

    public DefaultGetUsersHistory() {
        this.sqlLiteRepository = new DefaultSQLUserRepository();
    }

    public List<SQLUserEntity> execute(Context ctx) {
        return this.sqlLiteRepository.retrieveUsersHistory(ctx);
    }

    public Observable<Object> executeWithObservable(Context ctx) {
        return io.reactivex.Observable.create(emitter -> {
            emitter.onNext(execute(ctx));
        });
    }

}
