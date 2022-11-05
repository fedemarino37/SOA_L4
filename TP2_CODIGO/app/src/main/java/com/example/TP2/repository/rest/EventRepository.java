package com.example.TP2.repository.rest;

import android.content.Context;

import com.example.TP2.entity.RegisterEventRequest;
import com.example.TP2.entity.RegisterEventResponse;
import com.example.TP2.repository.exception.NetworkConnectionException;

import java.io.IOException;

public interface EventRepository {
    RegisterEventResponse registerEvent(Context ctx, RegisterEventRequest registerEventRequest, String token) throws NetworkConnectionException, IOException;
}
