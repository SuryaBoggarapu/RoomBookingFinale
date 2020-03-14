package com.a.roombooking.model;

import com.google.gson.annotations.SerializedName;

public class AvalableRoomsPojo {
    @SerializedName("id")
    private String id;

    @SerializedName("bname")
    private String bname;

    @SerializedName("rname")
    private String rname;


    @SerializedName("capacity")
    private String capacity;

    @SerializedName("equipment")
    private String equipment;

    @SerializedName("status")
    private String status;

    public AvalableRoomsPojo(String id,String bname, String rname, String capacity, String equipment, String status) {
        this.setBname(bname);
        this.setRname(rname);
        this.setCapacity(capacity);
        this.setEquipment(equipment);
        this.setStatus(status);
        this.setId(id);

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

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
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
}
