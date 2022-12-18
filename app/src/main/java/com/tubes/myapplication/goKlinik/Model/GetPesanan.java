package com.tubes.myapplication.goKlinik.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetPesanan {
    @SerializedName("status")
    String status;
    @SerializedName("message")
    String message;
    @SerializedName("data")
    List<Pesanan> listPesanan;

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

    public List<Pesanan> getListPesanan() {
        return listPesanan;
    }
    public void setListObat(List<Pesanan> listObat) {
        this.listPesanan = listObat;
    }
}
