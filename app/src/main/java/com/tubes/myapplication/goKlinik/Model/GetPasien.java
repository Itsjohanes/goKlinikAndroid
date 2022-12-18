package com.tubes.myapplication.goKlinik.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetPasien {
    @SerializedName("status")
    String status;
    @SerializedName("message")
    String message;
    @SerializedName("data")
    List<Pasien> listDataPasiens;

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

    public List<Pasien> getListDataNotes() {
        return listDataPasiens;
    }
    public void setListDataPasiens(List<Pasien> listDataPasiens) {
        this.listDataPasiens = listDataPasiens;
    }
}
