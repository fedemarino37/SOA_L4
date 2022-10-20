package com.example.TP2.repository;

import android.content.Context;

import com.example.TP2.entity.RegisterEventRequest;
import com.example.TP2.entity.RegisterEventResponse;

public interface EventRepository {
    RegisterEventResponse registerEvent(Context ctx, RegisterEventRequest registerEventRequest, String token);
}
