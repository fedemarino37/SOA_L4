package com.example.TP2.repository.sqlite;

import android.content.Context;

import com.example.TP2.entity.SQLUserEntity;

import java.util.List;

public interface SQLUserRepository {
    List<SQLUserEntity> retrieveUsersHistory(Context ctx);

    SQLUserEntity getUserData(Context ctx, String userEmail);
    void saveUserHistory(Context ctx, SQLUserEntity user);
    void insertNewUser(Context ctx, SQLUserEntity newUser);
    List<SQLUserEntity> getUsersTable (Context ctx);
    void deleteTables(Context ctx);
}
