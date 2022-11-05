package com.example.TP2.usecase;

import android.content.Context;

import com.example.TP2.repository.exception.NetworkConnectionException;

import java.io.IOException;

public interface RegisterEvent {
    void execute(Context ctx, String eventType, String description) throws NetworkConnectionException, IOException;
}
