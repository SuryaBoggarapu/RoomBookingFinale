package com.a.roombooking.model;

import com.google.gson.annotations.SerializedName;

public class RoomsPOJO {

    @SerializedName("id")
    private String id;

    @SerializedName("bname")
    private String bname;

    @SerializedName("rname")
    private String rname;


    @SerializedName("capacity")
    private String capacity;


    @SerializedName("hardware")
    private String hardware;

    @SerializedName("software")
    private String software;

    @SerializedName("descrip")
    private String descrip;

    @SerializedName("other")
    private String other;

    @SerializedName("booked_by")
    private String booked_by;


    @SerializedName("status")
    private String status;

    @SerializedName("img_url")
    private String img_url;





    public RoomsPOJO(String rname,String bname,String capacity,String hardware,String software,String descrip,String other,String booked_by,String status,String id,String img_url) {
      this.setRname(rname);
      this.setStatus(status);
      this.setBname(bname);
      this.setCapacity(capacity);
      this.setHardware(hardware);
      this.setSoftware(software);
      this.setDescrip(descrip);
      this.setOther(other);
      this.setBooked_by(booked_by);
      this.setId(id);
      this.setImg_url(img_url);

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getHardware() {
        return hardware;
    }

    public void setHardware(String hardware) {
        this.hardware = hardware;
    }

    public String getSoftware() {
        return software;
    }

    public void setSoftware(String software) {
        this.software = software;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getBooked_by() {
        return booked_by;
    }

    public void setBooked_by(String booked_by) {
        this.booked_by = booked_by;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}



