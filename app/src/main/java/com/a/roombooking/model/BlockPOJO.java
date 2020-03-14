package com.a.roombooking.model;

import com.google.gson.annotations.SerializedName;

public class BlockPOJO {
    @SerializedName("bname")
    private String bname;

    @SerializedName("img_url")
    private String img_url;




    public BlockPOJO(String bname,String img_url) {
        this.setBname(bname);
        this.setImg_url(img_url);
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}

