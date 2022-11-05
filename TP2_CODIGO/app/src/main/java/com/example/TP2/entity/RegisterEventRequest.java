package com.example.TP2.entity;

import com.google.gson.annotations.SerializedName;

public class RegisterEventRequest extends BaseEntityRequest {

    @SerializedName("env")
    private String environment;

    @SerializedName("type_events")
    private String eventType;

    @SerializedName("description")
    private String description;

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
