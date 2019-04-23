package com.example.dbms_app.model;

import com.google.gson.annotations.SerializedName;

public class BoolCheck {
    @SerializedName("res")
    int res;

    public int getRes() {
        return res;
    }

    public void setRes(int check) {
        this.res = check;
    }
}
