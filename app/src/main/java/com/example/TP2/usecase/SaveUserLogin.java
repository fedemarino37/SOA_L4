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
        // Como no se puede recibir por parametro en execute, lo hago atributo.
    }

    public Observable<Object> execute(Context ctx) {
        return io.reactivex.Observable.create(emitter -> {

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
            String currentDateAndTime = sdf.format(new Date());

            SQLUserEntity sqlUserEntity = sql.getUserData(ctx, this.userEmail);
            /* REcibo el mail y tengo que registrar el nombre y apellido, busco en la BD
            *   el nombre y ap a partir del mail recibido. */
            sqlUserEntity.setTimeStampLastAccess(currentDateAndTime.toString());
            // A esos datos, les seteo la fecha y hora de acceso (como string).

            // Todo: REVISAR ESTO: No estoy seguro de que deberia devolver esto.
            /*  El metodo devuelve un boolean indicando si fue existoso o no la operacion.*/
            emitter.onNext(sql.saveUserHistory(ctx,sqlUserEntity));
        });
    }

    public Observable<Object> executeWithObservable(Context ctx) {
        return io.reactivex.Observable.create(emitter -> {
            emitter.onNext(execute(ctx));
        });
    }
}
