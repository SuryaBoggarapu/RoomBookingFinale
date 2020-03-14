package com.a.roombooking.model;

import com.google.gson.annotations.SerializedName;

public class GetBlocksPojo {

    @SerializedName("bname")
    private
    String bname;

    public GetBlocksPojo(String bname)
    {
        this.setBname(bname);
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }
}
