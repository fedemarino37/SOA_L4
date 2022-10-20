package com.example.TP2.entity;

import com.google.gson.annotations.SerializedName;

public class EventEntity {

    @SerializedName("type_events")
    private String eventType;

    @SerializedName("dni")
    private String dni;

    @SerializedName("description")
    private String description;

    @SerializedName("id")
    private Integer id;

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
