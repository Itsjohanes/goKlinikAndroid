package com.tubes.myapplication.goKlinik.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PostPesanan {
    @SerializedName("status")
    String status;
    @SerializedName("message")
    String message;
    @SerializedName("data")
    List<Pesanan> listDataPesanan;

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

    public List<Pesanan> getListDataPesanan() {
        return listDataPesanan;
    }
    public void setListDataPesanan(List<Pesanan> listDataPesanan) {
        this.listDataPesanan = listDataPesanan;
    }
}

