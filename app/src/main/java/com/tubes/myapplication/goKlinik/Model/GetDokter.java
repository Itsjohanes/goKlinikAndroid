package com.tubes.myapplication.goKlinik.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetDokter {

    @SerializedName("status")
    String status;
    @SerializedName("message")
    String message;
    @SerializedName("data")
    List<Dokter> listDokter;

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

    public List<Dokter> getListDokter() {
        return listDokter;
    }
    public void setListDokter(List<Dokter> listDokter) {
        this.listDokter = listDokter;
    }

}
