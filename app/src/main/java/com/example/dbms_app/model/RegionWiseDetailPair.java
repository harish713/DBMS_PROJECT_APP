package com.example.dbms_app.model;

public class RegionWiseDetailPair {
    int id;
    String region;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public RegionWiseDetailPair(int id, String region) {
        this.id = id;
        this.region = region;
    }
}
