package com.example.TP2.repository.rest.restclient;

import android.content.Context;

import com.example.TP2.repository.exception.NetworkConnectionException;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.Map;

import io.reactivex.annotations.Nullable;

public interface RestClient {
    Response get(Context ctx, String url, @Nullable Map<String, String> headers) throws IOException, NetworkConnectionException;
    Response post(Context ctx, String url, String body, @Nullable Map<String, String> headers) throws IOException, NetworkConnectionException;
    Response put(Context ctx, String url, String body, @Nullable Map<String, String> headers) throws IOException, NetworkConnectionException;
    Response patch(Context ctx, String url, String body, @Nullable Map<String, String> headers) throws IOException, NetworkConnectionException;
}
