package com.example.TP2.repository.rest;

import android.content.Context;

import com.example.TP2.entity.DollarEntity;
import com.example.TP2.entity.mapper.DollarEntityJsonMapper;
import com.example.TP2.repository.exception.NetworkConnectionException;
import com.example.TP2.repository.rest.restclient.DefaultRestClient;
import com.example.TP2.repository.rest.restclient.RestClient;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class DefaultDollarRepository implements DollarRepository {

    private static final String BASE_URL = "https://api-dolar-argentina.herokuapp.com/api";
    private static final String OFFICIAL_DOLLAR = "/dolaroficial";
    private static final String BLUE_DOLLAR = "/dolarblue";
    private static final String MEP_DOLLAR = "/dolarbolsa";
    private static final String TOURIST_DOLLAR = "/dolarturista";

    private final RestClient client;
    private final DollarEntityJsonMapper dollarJsonMapper;

    public DefaultDollarRepository() {
        this.client = new DefaultRestClient();
        this.dollarJsonMapper = new DollarEntityJsonMapper();
    }

    @Override
    public DollarEntity retrieveOfficialDollar(Context ctx) throws IOException, NetworkConnectionException {
        Response response = client.get(ctx,BASE_URL + OFFICIAL_DOLLAR, null);
        String stringBody = response.body().string();

        DollarEntity dollarEntity = dollarJsonMapper.transformToEntity(stringBody);
        dollarEntity.setType("Oficial");

        return dollarEntity;
    }

    @Override
    public DollarEntity retrieveBlueDollar(Context ctx) throws IOException, NetworkConnectionException {
        Response response = client.get(ctx, BASE_URL + BLUE_DOLLAR, null);
        String stringBody = response.body().string();

        DollarEntity dollarEntity = dollarJsonMapper.transformToEntity(stringBody);
        dollarEntity.setType("Blue");

        return dollarEntity;
    }

    @Override
    public DollarEntity retrieveMEPDollar(Context ctx) throws IOException, NetworkConnectionException {
        Response response = client.get(ctx, BASE_URL + MEP_DOLLAR, null);
        String stringBody = response.body().string();

        DollarEntity dollarEntity = dollarJsonMapper.transformToEntity(stringBody);
        dollarEntity.setType("MEP");

        return dollarEntity;
    }

    @Override
    public DollarEntity retrieveTouristDollar(Context ctx) throws IOException, NetworkConnectionException {
        Response response = client.get(ctx, BASE_URL + TOURIST_DOLLAR, null);
        String stringBody = response.body().string();
        stringBody = stringBody.replace("No cotiza", "0");

        DollarEntity dollarEntity = dollarJsonMapper.transformToEntity(stringBody);
        dollarEntity.setType("Turista");

        return dollarEntity;
    }
}
