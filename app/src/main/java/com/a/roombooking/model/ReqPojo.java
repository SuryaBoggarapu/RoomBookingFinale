package com.a.roombooking.model;

import com.google.gson.annotations.SerializedName;

public class ReqPojo {
    @SerializedName("name")
    private
    String name;
    @SerializedName("status")
    private
    String status;

    public ReqPojo(String name,String status){
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
