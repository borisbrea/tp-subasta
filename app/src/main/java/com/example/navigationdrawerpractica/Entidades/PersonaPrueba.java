package com.example.navigationdrawerpractica.Entidades;

import java.io.Serializable;

public class PersonaPrueba implements Serializable {

    private Integer userId;
    private String  dni;
    private String  firstName;
    private String  lastName;
    private String  address;
    private String  email;
    private String  phone;
    private Integer code;
    private String  category;
    private String  password;
    private String  status;

    public PersonaPrueba(Integer userId, String dni, String firstName, String lastName, String address, String email, String phone, Integer code, String category, String password, String status) {
        this.userId     = userId;
        this.dni        = dni;
        this.firstName  = firstName;
        this.lastName   = lastName;
        this.address    = address;
        this.email      = email;
        this.phone      = phone;
        this.code       = code;
        this.category   = category;
        this.password   = password;
        this.status     = status;
    }

    public PersonaPrueba() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
