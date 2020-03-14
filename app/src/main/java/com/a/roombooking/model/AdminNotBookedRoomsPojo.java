package com.a.roombooking.model;

import com.google.gson.annotations.SerializedName;

public class AdminNotBookedRoomsPojo {
    @SerializedName("id")
    private String id;

    @SerializedName("bname")
    private String bname;

    @SerializedName("rname")
    private String rname;

    @SerializedName("capacity")
    private String capacity;

    @SerializedName("software")
    private String software;

    @SerializedName("hardware")
    private String hardware;

    @SerializedName("other")
    private String other;

    @SerializedName("reason_for_booking")
    private String reason_for_booking;


    @SerializedName("status")
    private String status;

    @SerializedName("booked_date")
    private String booked_date;

    @SerializedName("booked_by")
    private String booked_by;



    public AdminNotBookedRoomsPojo(String id, String bname, String rname, String capacity, String software, String status, String booked_date,String booked_by,String hardware,String other,String reason_for_booking) {
        this.setBname(bname);
        this.setRname(rname);
        this.setCapacity(capacity);
        this.setSoftware(software);
        this.setHardware(hardware);
        this.setOther(other);
        this.setReason_for_booking(reason_for_booking);

        this.setStatus(status);
        this.setId(id);
        this.setBooked_date(booked_date);
        this.setBooked_by(booked_by);

    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getBooked_by() {
        return booked_by;
    }

    public void setBooked_by(String booked_by) {
        this.booked_by = booked_by;
    }

    public String getSoftware() {
        return software;
    }

    public void setSoftware(String software) {
        this.software = software;
    }

    public String getHardware() {
        return hardware;
    }

    public void setHardware(String hardware) {
        this.hardware = hardware;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getReason_for_booking() {
        return reason_for_booking;
    }

    public void setReason_for_booking(String reason_for_booking) {
        this.reason_for_booking = reason_for_booking;
    }
}
