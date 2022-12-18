package com.tubes.myapplication.goKlinik.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetObat {
    @SerializedName("status")
    String status;
    @SerializedName("message")
    String message;
    @SerializedName("data")
    List<Obat> listObat;

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

    public List<Obat> getListObat() {
        return listObat;
    }
    public void setListObat(List<Obat> listObat) {
        this.listObat = listObat;
    }
}
