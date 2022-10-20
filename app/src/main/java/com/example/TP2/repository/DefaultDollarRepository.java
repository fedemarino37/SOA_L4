package com.example.TP2.repository;

import android.content.Context;

import com.example.TP2.entity.DollarEntity;
import com.example.TP2.entity.mapper.DollarEntityJsonMapper;
import com.example.TP2.repository.exception.NetworkConnectionException;
import com.example.TP2.repository.net.DefaultRestClient;
import com.example.TP2.repository.net.RestClient;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class DefaultDollarRepository implements DollarRepository {

    private static final String BASE_URL = "https://api-dolar-argentina.herokuapp.com/api";
    private static final String OFFICIAL_DOLLAR = "/dolaroficial";
    private static final String BLUE_DOLLAR = "/dolarblue";
    private static final String MEP_DOLLAR = "/dolarbolsa";

    private final DollarEntityJsonMapper dollarJsonMapper;
    private final RestClient client;

    public DefaultDollarRepository() {
        this.dollarJsonMapper = new DollarEntityJsonMapper();
        this.client = new DefaultRestClient();
    }


    @Override
    public DollarEntity retrieveOfficialDollar(Context ctx) throws IOException, NetworkConnectionException {
        Response response = client.get(ctx,BASE_URL + OFFICIAL_DOLLAR);
        String stringBody = response.body().string();

        return dollarJsonMapper.transformDollarEntity(stringBody);
    }

    @Override
    public DollarEntity retrieveBlueDollar(Context ctx) throws IOException, NetworkConnectionException {
        Response response = client.get(ctx, BASE_URL + BLUE_DOLLAR);
        String stringBody = response.body().string();

        return dollarJsonMapper.transformDollarEntity(stringBody);
    }

    @Override
    public DollarEntity retrieveMEPDollar(Context ctx) throws IOException, NetworkConnectionException {
        Response response = client.get(ctx, BASE_URL + MEP_DOLLAR);
        String stringBody = response.body().string();

        return dollarJsonMapper.transformDollarEntity(stringBody);
    }
}
