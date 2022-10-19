package com.example.TP2.data.entity;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;

public class DollarEntity {

    @SerializedName("fecha")
    private LocalDateTime dateTime;

    @SerializedName("compra")
    private Float buyValue;

    @SerializedName("venta")
    private Float sellValue;

    private String type;

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Float getBuyValue() {
        return buyValue;
    }

    public void setBuyValue(Float buyValue) {
        this.buyValue = buyValue;
    }

    public Float getSellValue() {
        return sellValue;
    }

    public void setSellValue(Float sellValue) {
        this.sellValue = sellValue;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
