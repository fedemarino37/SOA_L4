package com.example.TP2.usecase;

import android.content.Context;

import com.example.TP2.entity.SQLUserEntity;
import com.example.TP2.repository.sqlite.DefaultSQLUserRepository;
import com.example.TP2.repository.sqlite.SQLUserRepository;

import io.reactivex.Observable;

public class CreateSQLUserEntity {
    SQLUserRepository sqlUserRepository;

    public CreateSQLUserEntity() {
        sqlUserRepository = new DefaultSQLUserRepository();
    }

    public void execute(Context ctx, String name, String lastName, String email) {

        SQLUserEntity newUser = new SQLUserEntity();
        newUser.setName(name);
        newUser.setLastName(lastName);
        newUser.setEmail(email);

        sqlUserRepository.insertNewUser(ctx,newUser);
    }

    public Observable<Void> executeWithObservable(Context ctx,String name, String lastName, String email) {
        return io.reactivex.Observable.create(emitter -> {
            execute(ctx, name, lastName, email);
            emitter.onNext(null);
        });
    }
}
