package com.example.TP2.repository;

import android.content.Context;

import com.example.TP2.entity.DollarEntity;
import com.example.TP2.repository.exception.NetworkConnectionException;

import java.io.IOException;

public interface DollarRepository {
    DollarEntity retrieveOfficialDollar(Context ctx) throws IOException, NetworkConnectionException;
    DollarEntity retrieveBlueDollar(Context ctx) throws IOException, NetworkConnectionException;
    DollarEntity retrieveMEPDollar(Context ctx) throws IOException, NetworkConnectionException;
}