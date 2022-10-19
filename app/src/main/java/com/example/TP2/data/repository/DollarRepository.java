package com.example.TP2.data.repository;

import com.example.TP2.data.entity.DollarEntity;
import com.example.TP2.data.entity.mapper.DollarEntityJsonMapper;
import com.example.TP2.data.exception.NetworkConnectionException;
import com.example.TP2.data.net.RestClient;
import com.example.TP2.domain.repository.DollarRepositoryInterface;
import com.squareup.okhttp.Response;

import java.io.IOException;

import javax.inject.Inject;


public class DollarRepository implements DollarRepositoryInterface {

    private static final String BASE_URL = "https://api-dolar-argentina.herokuapp.com/api";
    private static final String OFFICIAL_DOLLAR = "/dolaroficial";
    private static final String BLUE_DOLLAR = "/dolarblue";
    private static final String MEP_DOLLAR = "/dolarbolsa";

    private final DollarEntityJsonMapper dollarJsonMapper;
    private final RestClient client;


    @Inject
    public DollarRepository(DollarEntityJsonMapper dollarJsonMapper, RestClient client) {
        this.dollarJsonMapper = dollarJsonMapper;
        this.client = client;
    }


    @Override
    public DollarEntity retrieveOfficialDollar() throws IOException, NetworkConnectionException {
        Response response = client.get(BASE_URL + OFFICIAL_DOLLAR);
        String stringBody = response.body().string();

        return dollarJsonMapper.transformDollarEntity(stringBody);
    }

    @Override
    public DollarEntity retrieveBlueDollar() throws NetworkConnectionException, IOException {
        Response response = client.get(BASE_URL + BLUE_DOLLAR);
        String stringBody = response.body().string();

        return dollarJsonMapper.transformDollarEntity(stringBody);
    }

    @Override
    public DollarEntity retrieveMEPDollar() throws IOException, NetworkConnectionException {
        Response response = client.get(BASE_URL + MEP_DOLLAR);
        String stringBody = response.body().string();

        return dollarJsonMapper.transformDollarEntity(stringBody);
    }
}
