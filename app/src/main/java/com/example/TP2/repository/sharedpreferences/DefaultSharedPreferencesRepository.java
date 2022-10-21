package com.example.TP2.repository.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

public class DefaultSharedPreferencesRepository implements SharedPreferencesRepository {

    private static final String PREFERENCES_NAME = "com.example.TP2";
    private static final String TOKEN_PREF = "token";


    @Override
    public String getToken(Context ctx) {
        SharedPreferences prefs = ctx.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);

        String token = prefs.getString(TOKEN_PREF, "");

        // TODO: throw exception if token not found??

        return token;
    }

    @Override
    public Void saveToken(Context ctx, String token) {
        SharedPreferences prefs = ctx.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(TOKEN_PREF, token);
        editor.apply();

        return null;
    }
}
