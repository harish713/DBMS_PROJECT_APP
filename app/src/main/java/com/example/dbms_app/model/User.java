package com.example.dbms_app.model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("username")
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @SerializedName("password")
    private String password;
    @SerializedName("result")
    int result;
    public User(String email,String password,int result){
        this.username = email;
        this.password = password;
    }
}
