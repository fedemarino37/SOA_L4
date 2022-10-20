package com.example.TP2.repository;

import android.content.Context;

import com.example.TP2.entity.RegisterEventRequest;
import com.example.TP2.entity.RegisterEventResponse;
import com.example.TP2.entity.mapper.RegisterEventResponseJsonMapper;
import com.example.TP2.repository.net.DefaultRestClient;
import com.example.TP2.repository.net.RestClient;

public class DefaultEventRepository implements EventRepository {

    private static final String BASE_URL = "http://so-unlam.net.ar/api/api";
    private static final String REGISTER_EVENT = "/event";

    private final RestClient client;
    private final RegisterEventResponseJsonMapper registerEventResponseJsonMapper;

    public DefaultEventRepository() {
        this.client = new DefaultRestClient();
        this.registerEventResponseJsonMapper = new RegisterEventResponseJsonMapper();
    }

    @Override
    public RegisterEventResponse registerEvent(Context ctx, RegisterEventRequest registerEventRequest, String token) {
        //TODO: continue!!

        return null;
    }
}
