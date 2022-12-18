package com.tubes.myapplication.goKlinik.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetJadwal {
    @SerializedName("status")
    String status;
    @SerializedName("message")
    String message;
    @SerializedName("data")
    List<Jadwal> listjadwal;

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public List<Jadwal> getListjadwal() {
        return listjadwal;
    }
    public void setListJadwal(List<Jadwal> listJadwal) {
        this.listjadwal= listJadwal;
    }
}
