package com.a.roombooking.model;

import com.google.gson.annotations.SerializedName;

public class MyBookedRoomsPojo {
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

    @SerializedName("booked_date")
    private String booked_date;


    @SerializedName("status")
    private String status;

    @SerializedName("booked_time")
    private String booked_time;

    @SerializedName("end_date")
    private String end_date;

    @SerializedName("reason_for_booking")
    private String reason_for_booking;

    @SerializedName("duration")
    private String duration;


    public MyBookedRoomsPojo(String rname,String bname,String capacity,String hardware,String software,String descrip,String other,String booked_date,String status,String id,String booked_time,String end_date,String reason_for_booking,String duration) {
        this.setRname(rname);
        this.setStatus(status);
        this.setBname(bname);
        this.setCapacity(capacity);
        this.setHardware(hardware);
        this.setSoftware(software);
        this.setDescrip(descrip);
        this.setOther(other);
        this.setBooked_date(booked_date);
        this.setId(id);
        this.setBooked_time(booked_time);
        this.setEnd_date(end_date);
        this.setreason(reason_for_booking);
        this.setDuration(duration);

    }

    public String getReason() {
        return reason_for_booking;
    }

    public void setreason(String status) {
        this.reason_for_booking = reason_for_booking;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String status) {
        this.duration = duration;
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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBooked_date() {
        return booked_date;
    }

    public void setBooked_date(String booked_date) {
        this.booked_date = booked_date;
    }

    public String getBooked_time() {
        return booked_time;
    }

    public void setBooked_time(String booked_time) {
        this.booked_time = booked_time;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }
}

