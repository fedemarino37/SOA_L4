package com.example.TP2.data.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.TP2.data.exception.NetworkConnectionException;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;



public class RestClient implements RestClientInterface, Callable<String>  {

    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_VALUE_JSON = "application/json; charset=utf-8";
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private final OkHttpClient okHttpClient;
    private final Context context;

    @Inject
    public RestClient(Context context) {
        this.okHttpClient = createClient();
        this.context = context;
    }

    private OkHttpClient createClient() {
        final OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setReadTimeout(10000, TimeUnit.MILLISECONDS);
        okHttpClient.setConnectTimeout(15000, TimeUnit.MILLISECONDS);

        return okHttpClient;
    }

    @Override
    public String call() throws Exception {
        return null;
    }

    @Override
    public Response get(String url) throws IOException, NetworkConnectionException {
        if (noInternetConnection()) {
            throw new NetworkConnectionException();
        }

        final Request request = new Request.Builder()
                .url(url)
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_VALUE_JSON)
                .get()
                .build();

        return okHttpClient.newCall(request).execute();
    }

    @Override
    public Response post(String url, String body) throws IOException, NetworkConnectionException {
        if (noInternetConnection()) {
            throw new NetworkConnectionException();
        }

        RequestBody requestBody = RequestBody.create(JSON, body);
        final Request request = new Request.Builder()
                .url(url)
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_VALUE_JSON)
                .post(requestBody)
                .build();

        return okHttpClient.newCall(request).execute();
    }

    @Override
    public Response put(String url, String body) throws IOException, NetworkConnectionException {
        if (noInternetConnection()) {
            throw new NetworkConnectionException();
        }

        RequestBody requestBody = RequestBody.create(JSON, body);

        final Request request = new Request.Builder()
                .url(url)
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_VALUE_JSON)
                .put(requestBody)
                .build();

        return okHttpClient.newCall(request).execute();
    }

    @Override
    public Response patch(String url, String body) throws IOException, NetworkConnectionException {
        if (noInternetConnection()) {
            throw new NetworkConnectionException();
        }

        RequestBody requestBody = RequestBody.create(JSON, body);

        final Request request = new Request.Builder()
                .url(url)
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_VALUE_JSON)
                .patch(requestBody)
                .build();

        return okHttpClient.newCall(request).execute();
    }

    /**
     * Checks if the device has any active internet connection.
     *
     * @return true device with internet connection, otherwise false.
     */
    private boolean noInternetConnection() {
        boolean isConnected;

        ConnectivityManager connectivityManager =
                (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());

        return !isConnected;
    }
}
