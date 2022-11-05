package com.example.TP2.repository.sharedpreferences;

import android.content.Context;

public interface SharedPreferencesRepository {
    String getToken(Context ctx);
    Void saveToken(Context ctx, String token);
}
