package com.example.TP2.usecase;

import android.content.Context;
import android.widget.Toast;

import com.example.TP2.entity.LoginUserRequest;
import com.example.TP2.entity.SQLUserEntity;
import com.example.TP2.repository.exception.HttpUnexpectedErrorException;
import com.example.TP2.repository.exception.SQLUserNotFoundException;
import com.example.TP2.repository.sqlite.DefaultSQLUserRepository;
import com.example.TP2.repository.sqlite.SQLUserRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import io.reactivex.Observable;

public class SaveUserLogin {
    SQLUserRepository sql;

    public SaveUserLogin() {
        sql = new DefaultSQLUserRepository();
    }

    public void execute(Context ctx, String email) throws SQLUserNotFoundException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
        sdf.setTimeZone(TimeZone.getTimeZone("GMT-03:00"));
        String currentDateAndTime = sdf.format(new Date());

        SQLUserEntity sqlUserEntity = sql.getUserData(ctx,email);

        if(sqlUserEntity != null) {
            sqlUserEntity.setTimeStampLastAccess(currentDateAndTime);
            sql.saveUserHistory(ctx,sqlUserEntity);
        } else {
           throw new SQLUserNotFoundException();
        }


    }

    public Observable<Void> executeWithObservable(Context ctx, LoginUserRequest loginUserRequest) {

        return io.reactivex.Observable.create(emitter -> {
            execute(ctx,loginUserRequest.getEmail());
            emitter.onNext(null);
        });
    }
}
