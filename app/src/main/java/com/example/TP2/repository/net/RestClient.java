package com.example.TP2.repository.net;

import android.content.Context;

import com.example.TP2.repository.exception.NetworkConnectionException;
import com.squareup.okhttp.Response;

import java.io.IOException;

public interface RestClient {
    public Response get(Context ctx, String url) throws IOException, NetworkConnectionException;
    public Response post(Context ctx, String url, String body) throws IOException, NetworkConnectionException;
    public Response put(Context ctx, String url, String body) throws IOException, NetworkConnectionException;
    public Response patch(Context ctx, String url, String body) throws IOException, NetworkConnectionException;
}
