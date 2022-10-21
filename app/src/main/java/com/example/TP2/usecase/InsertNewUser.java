package com.example.TP2.usecase;

import android.content.Context;

import com.example.TP2.entity.SQLUserEntity;
import com.example.TP2.repository.sqlite.DefaultSQLUserRepository;
import com.example.TP2.repository.sqlite.SQLUserRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.reactivex.Observable;

public class InsertNewUser {
    SQLUserRepository sql;
    SQLUserEntity newUser;

    public InsertNewUser(SQLUserEntity newUser) {
        sql = new DefaultSQLUserRepository();
        this.newUser = newUser;
    }

    public Observable<Object> execute(Context ctx) {
        return io.reactivex.Observable.create(emitter -> {
            // Todo: Verificar el retorno de este metodo. Devuelve si fue exitosa o no la operacion.
            emitter.onNext(sql.insertNewUser(ctx,this.newUser));
        });
    }

    public Observable<Object> executeWithObservable(Context ctx) {
        return io.reactivex.Observable.create(emitter -> {
            emitter.onNext(execute(ctx));
        });
    }
}
