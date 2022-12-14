package com.example.TP2.entity;

public class SQLUserEntity {
    private String name;
    private String lastName;
    private String email;
    private String timeStampLastAccess;

    public SQLUserEntity() {
    }

    public SQLUserEntity(String name, String lastName, String email,String lastAccess) {
        this.name = name;
        this.lastName = lastName;
        this.email = email != null ? email : "";
        this.timeStampLastAccess = lastAccess != null ? lastAccess : "";
    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTimeStampLastAccess() { return timeStampLastAccess; }

    public void setName(String name) { this.name = name; }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setTimeStampLastAccess(String timeStampLastAccess) {
        this.timeStampLastAccess = timeStampLastAccess;
    }
}
