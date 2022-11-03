package com.example.TP2.usecase;

import android.content.Context;

import com.example.TP2.BuildConfig;
import com.example.TP2.entity.RegisterEventRequest;
import com.example.TP2.repository.exception.NetworkConnectionException;
import com.example.TP2.repository.rest.DefaultEventRepository;
import com.example.TP2.repository.rest.EventRepository;
import com.example.TP2.repository.sharedpreferences.DefaultSharedPreferencesRepository;
import com.example.TP2.repository.sharedpreferences.SharedPreferencesRepository;

import java.io.IOException;

public class RegisterEvent {
    private final SharedPreferencesRepository sharedPreferencesRepository;
    private final EventRepository eventRepository;

    public RegisterEvent() {
        sharedPreferencesRepository = new DefaultSharedPreferencesRepository();
        eventRepository = new DefaultEventRepository();
    }

    public void execute(Context ctx, String eventType, String description) throws NetworkConnectionException, IOException {
        String token = sharedPreferencesRepository.getToken(ctx);

        RegisterEventRequest registerEventRequest = new RegisterEventRequest();
        registerEventRequest.setEventType(eventType);
        registerEventRequest.setDescription(description);
        registerEventRequest.setEnvironment(BuildConfig.ENVIRONMENT);

        eventRepository.registerEvent(ctx, registerEventRequest, token);
    }
}
