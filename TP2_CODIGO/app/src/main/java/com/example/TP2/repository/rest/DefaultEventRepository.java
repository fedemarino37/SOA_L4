package com.example.TP2.repository.rest;

import android.content.Context;

import com.example.TP2.entity.RegisterEventRequest;
import com.example.TP2.entity.RegisterEventResponse;
import com.example.TP2.entity.mapper.RegisterEventResponseJsonMapper;
import com.example.TP2.repository.exception.NetworkConnectionException;
import com.example.TP2.repository.rest.restclient.DefaultRestClient;
import com.example.TP2.repository.rest.restclient.RestClient;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
    public RegisterEventResponse registerEvent(Context ctx, RegisterEventRequest registerEventRequest, String token) throws NetworkConnectionException, IOException {
        Response response = client.post(ctx, BASE_URL+REGISTER_EVENT, registerEventRequest.toJson(), buildMapHeaders(token));

        String stringBody = response.body().string();

        return registerEventResponseJsonMapper.transformToEntity(stringBody);
    }

    private Map<String, String> buildMapHeaders(String token) {
        Map<String, String> mapHeaders = new HashMap<String, String>();

        mapHeaders.put("Authorization", "Bearer "+token);

        return mapHeaders;
    }
}
