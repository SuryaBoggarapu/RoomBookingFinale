package com.a.roombooking.model;

import com.google.gson.annotations.SerializedName;

public class EditProfilePojo {
    @SerializedName("name")
    private String name ;

    @SerializedName("phone")
    private
    String phone ;

    @SerializedName("emailid")
    private
    String emailid ;



    @SerializedName("pwd")
    private
    String pwd ;



    EditProfilePojo(String name, String phone, String emailid, String pwd){

        this.setName(name);
        this.setPhone(phone);
        this.setEmailid(emailid);
        this.setPwd(pwd);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
