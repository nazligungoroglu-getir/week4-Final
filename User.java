package com.academy.model;

public class User {
    private String name;
    private String userName;
    private String password;
    private String phone;
    private String address;

    public User(String name, String userName, String password, String phone, String address) {
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.phone = phone;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }
}
