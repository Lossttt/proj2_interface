package com.example.hhs_proj2;

public class User {
    private String email;
    private String password;
    private String userID;


    public User(String email, String password, String userID) {
        this.email = email;
        this.password = password;
        this.userID = userID;
    }


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUserID() {
        return userID;
    }
}

