package com.example.navigationdrawerpractica.Entidades.requestEntities;

import java.io.Serializable;

public class UserUpdateRequest implements Serializable {

    private String userId;
    private String email;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String password;

    public UserUpdateRequest() {
    }

    public UserUpdateRequest(String userId, String email, String firstName, String lastName, String address, String phone, String password) {
        this.email = email;
        this.userId= userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
