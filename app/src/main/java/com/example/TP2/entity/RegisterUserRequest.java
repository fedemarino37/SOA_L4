package com.example.TP2.entity;

import com.google.gson.annotations.SerializedName;

public class RegisterUserRequest extends BaseEntityRequest {

    @SerializedName("env")
    private String environment;

    @SerializedName("name")
    private String name;

    @SerializedName("lastname")
    private String lastName;

    @SerializedName("dni")
    private Integer dni;

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    @SerializedName("commission")
    private Integer commission;

    @SerializedName("group")
    private Integer group;

    public RegisterUserRequest() {
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getCommission() {
        return commission;
    }

    public void setCommission(Integer commission) {
        this.commission = commission;
    }

    public Integer getGroup() {
        return group;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }
}
