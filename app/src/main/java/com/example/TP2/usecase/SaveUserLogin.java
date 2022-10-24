package com.example.TP2.usecase;

import android.content.Context;

import com.example.TP2.entity.LoginUserRequest;
import com.example.TP2.entity.SQLUserEntity;
import com.example.TP2.repository.sqlite.DefaultSQLUserRepository;
import com.example.TP2.repository.sqlite.SQLUserRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.reactivex.Observable;

public class SaveUserLogin {
    SQLUserRepository sql;

    public SaveUserLogin() {
        sql = new DefaultSQLUserRepository();
    }

    public void execute(Context ctx, String email) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
        // Todo: Modificar el formato, la hora se registra dos horas adelatando.
        String currentDateAndTime = sdf.format(new Date());

        SQLUserEntity sqlUserEntity = sql.getUserData(ctx,email);
        /* REcibo el mail y tengo que registrar el nombre y apellido, busco en la BD
         *   el nombre y ap a partir del mail recibido. */
        sqlUserEntity.setTimeStampLastAccess(currentDateAndTime);
        // A esos datos, les seteo la fecha y hora de acceso (como string).

        sql.saveUserHistory(ctx,sqlUserEntity);
    }

    public Observable<Void> executeWithObservable(Context ctx, LoginUserRequest loginUserRequest) {

        return io.reactivex.Observable.create(emitter -> {
            execute(ctx,loginUserRequest.getEmail());
            emitter.onNext(null);
        });
    }
}
