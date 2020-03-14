package com.a.roombooking.model;

import com.google.gson.annotations.SerializedName;

public class AdminGetAllRoomsPojo {
    @SerializedName("id")
    private String id;

    @SerializedName("bname")
    private String bname;

    @SerializedName("rname")
    private String rname;

    @SerializedName("software")
    private String software;

    @SerializedName("hardware")
    private String hardware;

    @SerializedName("descrip")
    private String descrip;

    @SerializedName("capacity")
    private String capacity;



    public AdminGetAllRoomsPojo(String id, String bname, String rname, String software, String hardware, String descrip,String capacity) {
        this.setBname(bname);
        this.setRname(rname);
        this.setSoftware(software);
        this.setHardware(hardware);
        this.setDescrip(descrip);
        this.setId(id);
        this.setCapacity(capacity);

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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }
}
