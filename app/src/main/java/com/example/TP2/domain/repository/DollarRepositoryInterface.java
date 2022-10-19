package com.example.TP2.domain.repository;

import com.example.TP2.data.entity.DollarEntity;
import com.example.TP2.data.exception.NetworkConnectionException;

import java.io.IOException;

public interface DollarRepositoryInterface {
    DollarEntity retrieveOfficialDollar() throws IOException, NetworkConnectionException;
    DollarEntity retrieveBlueDollar() throws NetworkConnectionException, IOException;
    DollarEntity retrieveMEPDollar() throws IOException, NetworkConnectionException;
}
