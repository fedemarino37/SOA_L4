package com.example.TP2.usecase;

import android.content.Context;

import com.example.TP2.entity.SQLUserEntity;
import com.example.TP2.repository.sqlite.DefaultSQLUserRepository;
import com.example.TP2.repository.sqlite.SQLUserRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.reactivex.Observable;

public class SaveUserLogin {
    SQLUserRepository sql;
    String userEmail;

    public SaveUserLogin(String userEmail) {
        sql = new DefaultSQLUserRepository();
        this.userEmail = userEmail;
    }

    public Observable<Object> execute(Context ctx) {
        return io.reactivex.Observable.create(emitter -> {

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
            String currentDateAndTime = sdf.format(new Date());

            SQLUserEntity sqlUserEntity = sql.getUserData(ctx, this.userEmail);
            sqlUserEntity.setTimeStampLastAccess(currentDateAndTime.toString());

            emitter.onNext(sql.saveUserHistory(ctx,sqlUserEntity));
        });
    }

    public Observable<Object> executeWithObservable(Context ctx) {
        return io.reactivex.Observable.create(emitter -> {
            emitter.onNext(execute(ctx));
        });
    }

    // Todo: crear atributo de SQL user Repository
    // Todo: Crear constructor e instanciar el atributo de SQLUserR
    // Todo: crear metodo execute. El mismo debe recibir el parametro context y email
    //  Crear entidad SQL user entity y setearle el timestamp.
    // Todo:  crear metodo execute with observable



}
