package com.example.TP2.repository.sqlLite;

import android.content.Context;

import com.example.TP2.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

public interface SQLLiteRepository {
    List<UserEntity> retrieveUsersHistory(Context ctx);
}
