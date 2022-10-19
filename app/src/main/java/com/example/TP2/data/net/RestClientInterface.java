package com.example.TP2.data.net;


import com.example.TP2.data.exception.NetworkConnectionException;
import com.squareup.okhttp.Response;

import java.io.IOException;

public interface RestClientInterface {

    public Response get(String url) throws IOException, NetworkConnectionException;
    public Response post(String url, String body) throws IOException, NetworkConnectionException;
    public Response put(String url, String body) throws IOException, NetworkConnectionException;
    public Response patch(String url, String body) throws IOException, NetworkConnectionException;
}
