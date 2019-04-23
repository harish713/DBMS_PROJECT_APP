package com.example.dbms_app.model;

import com.google.gson.annotations.SerializedName;

public class SignUpUser {
    int res;

    public int getResult() {
        return res;
    }

    public void setResult(int result) {
        this.res = result;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public String getAadhar() {
        return aadhaar;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    private String password;
    String aadhaar;
    public SignUpUser(String name,String password,String aadhaar,int result){
        this.name = name;
        this.password = password;
        this.aadhaar = aadhaar;
        this.res = result;
    }
}
