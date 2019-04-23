package com.example.dbms_app.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Election implements Parcelable {

    public Election(String type, int noOfConst, String dates, int eligibility) {
        this.type = type;
        this.noOfConst = noOfConst;
        this.dates = dates;
        this.eligibility = eligibility;
    }

    public Election(Parcel in){
        this.type = in.readString();
        this.noOfConst = in.readInt();
        this.dates =  in.readString();
        this.eligibility = in.readInt();
    }

    // TO BE DONE!!!
    @SerializedName("ID")
    private String id;
    @SerializedName("TYPE")
    private String type;
    @SerializedName("NO_OF_CONST")
    private int noOfConst;
    @SerializedName("DATES")
    private String dates;
    @SerializedName("ELIGIBILITY")
    private int eligibility;

    public static final Creator<Election> CREATOR = new Creator<Election>() {
        @Override
        public Election createFromParcel(Parcel in) {
            return new Election(in);
        }

        @Override
        public Election[] newArray(int size) {
            return new Election[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNoOfConst() {
        return noOfConst;
    }

    public void setNoOfConst(int noOfConst) {
        this.noOfConst = noOfConst;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public int getEligibility() {
        return eligibility;
    }

    public void setEligibility(int eligibility) {
        this.eligibility = eligibility;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(this.type);
        dest.writeInt(this.noOfConst);
        dest.writeString(this.dates);
        dest.writeInt(this.eligibility);
    }
}
