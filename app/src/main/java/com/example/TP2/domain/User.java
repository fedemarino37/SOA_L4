package com.example.TP2.domain;

public class User {
    private final int userId;

    public User(int userId) {
        this.userId = userId;
    }

    private String fullName;
    private String email;

    public int getUserId() {
        return userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
