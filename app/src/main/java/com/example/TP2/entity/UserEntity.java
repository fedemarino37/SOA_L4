package com.example.TP2.entity;

public class UserEntity {
    private String name, lastName, timeStampLastAccess;

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTimeStampLastAccess() {
        return timeStampLastAccess;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setTimeStampLastAccess(String timeStampLastAccess) {
        this.timeStampLastAccess = timeStampLastAccess;
    }
}
